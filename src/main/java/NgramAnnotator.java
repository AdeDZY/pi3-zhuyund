import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import type.Answer;
import type.ComponentAnnotation;
import type.Param;
import type.Question;
import type.Token;
import type.Ngram;

/**
 * Annotate N-gram spans
 * @author zhuyund
 *
 */
public class NgramAnnotator extends JCasAnnotator_ImplBase {

	public final String id = "NgramAnnotator";

	private int n;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		// Get config. parameter value
		this.n = ((Integer) aContext.getConfigParameterValue("n")).intValue();
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// Get Param
		FSIndex paramIndex = aJCas.getAnnotationIndex(Param.type);
		Param param = (Param) paramIndex.iterator().next();
		this.n = param.getN();

		// get annotation indexes
		FSIndex answerIndex = aJCas.getAnnotationIndex(Answer.type);
		FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
		FSIndex tokenIndex = aJCas.getAnnotationIndex(Token.type);
		String docText = aJCas.getDocumentText();

		// get all tokens
		ArrayList<Token> allTokens = new ArrayList<Token>();
		Iterator tokenIter = tokenIndex.iterator();
		while (tokenIter.hasNext()) {
			Token tokenAnnotation = (Token) tokenIter.next();
			allTokens.add(tokenAnnotation);
		}

		
		// find n-grams in the question
		Iterator questionIter = questionIndex.iterator();
		Question question = (Question) questionIter.next();
		int endQuestion = question.getEnd();
		int currTokenIndex = 0;
		while (true) {
			if (currTokenIndex + n > allTokens.size())
				break;
			if (allTokens.get(currTokenIndex + n - 1).getEnd() > endQuestion) {
				currTokenIndex += n - 1;
				break;
			}

			Ngram ngram = new Ngram(aJCas);
			ngram.setBegin(allTokens.get(currTokenIndex).getBegin());
			ngram.setEnd(allTokens.get(currTokenIndex + n - 1).getEnd());
			ngram.setComponentId(this.id);
			ngram.setScore(1);

			List<ComponentAnnotation> tokens = new ArrayList<ComponentAnnotation>();
			String ngramText = "";
			for (int i = 0; i < n; i++) {
				tokens.add(allTokens.get(currTokenIndex + i));
				ngramText += docText.substring(tokens.get(i).getBegin(), tokens
						.get(i).getEnd());
				ngramText += " ";
			}
			ngram.setTokens(FSListCreator.createFSList(aJCas, tokens));
			ngram.setText(ngramText.trim());
			ngram.addToIndexes();
			currTokenIndex += 1;
		}

		// find n-grams in answers
		Iterator answerIter = answerIndex.iterator();
		while (answerIter.hasNext()) {
			Answer answer = (Answer) answerIter.next();
			int endAnswer = answer.getEnd();
			while (true) {
				if (currTokenIndex + n > allTokens.size())
					break;

				if (allTokens.get(currTokenIndex + n - 1).getEnd() > endAnswer) {
					currTokenIndex += n - 1;
					break;
				}

				Ngram ngram = new Ngram(aJCas);
				ngram.setBegin(allTokens.get(currTokenIndex).getBegin());
				ngram.setEnd(allTokens.get(currTokenIndex + n - 1).getEnd());
				ngram.setComponentId(this.id);
				ngram.setScore(1);

				List<ComponentAnnotation> tokens = new ArrayList<ComponentAnnotation>();
				String ngramText = "";
				for (int i = 0; i < n; i++) {
					tokens.add(allTokens.get(currTokenIndex + i));
					ngramText += docText.substring(tokens.get(i).getBegin(),
							tokens.get(i).getEnd());
					ngramText += " ";
				}
				ngram.setTokens(FSListCreator.createFSList(aJCas, tokens));
				ngram.setText(ngramText.trim());
				ngram.addToIndexes();
				currTokenIndex += 1;
			}
		}
	}
}
