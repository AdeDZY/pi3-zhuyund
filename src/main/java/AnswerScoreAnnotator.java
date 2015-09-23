import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;

import type.Answer;
import type.AnswerScore;
import type.InputDocument;
import type.Ngram;
import type.Question;
import type.Token;

/**
 * Calculate a score for each answer
 * @author zhuyund
 *
 */
public class AnswerScoreAnnotator extends JCasAnnotator_ImplBase {

	public final String id = "AnswerScoreAnnotator";

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		FSIndex documentIndex = aJCas.getAnnotationIndex(InputDocument.type);
	    FSIndex ngramIndex = aJCas.getAnnotationIndex(Ngram.type);
	    FSIndex answerIndex = aJCas.getAnnotationIndex(Answer.type);
	    
	    Iterator docIter = documentIndex.iterator();
	    InputDocument document = (InputDocument) docIter.next();	
	    Question question = document.getQuestion();
	    Iterator ngramIter = ngramIndex.iterator();
	    
	    // Read all ngrams into memory and sort
	    ArrayList<Ngram> allNgrams = new ArrayList<Ngram>();
	    while(ngramIter.hasNext()){
	    	Ngram ngramAnnotation = (Ngram) ngramIter.next();
	    	allNgrams.add(ngramAnnotation);
	    }
	    
	   
		// find Ngrams in the question
	    float M = 0;
	    HashMap<String, Integer> questionNgrams = new HashMap<String, Integer>();
	    int endQuestion = question.getEnd();
	    int i = 0;
	    while(i < allNgrams.size()){
	    	if(allNgrams.get(i).getEnd() <= endQuestion){
	    		if(questionNgrams.get(allNgrams.get(i).getText()) != null)
	    			questionNgrams.put(allNgrams.get(i).getText(), questionNgrams.get(allNgrams.get(i).getText()) + 1);
	    		else
	    			questionNgrams.put(allNgrams.get(i).getText(), 0);	    		
	    		i += 1;
	    	}
	    	else{
	    		break;
	    	}
	    }
	    
	   
	    // find Ngrams in each answer, and scoring
	   
	    Iterator answerIter = answerIndex.iterator();
	    while(answerIter.hasNext()){
	    	Answer answer = (Answer)answerIter.next();
	    	int endAnswer = answer.getEnd();
	    	float score = 0;
	    	int len = 0;
	    	while(i < allNgrams.size()){
		    	if(allNgrams.get(i).getEnd() <= endAnswer){
		    		String ngramText = allNgrams.get(i).getText();
		    		if(questionNgrams.get(ngramText) != null){
		    			score += 1;
		    		}
		    		i += 1;
		    		len += 1;
		    	}
		    	else{
		    		break;
		    	}
		    }
	    	score = score/len;
	    	AnswerScore answerScore = new AnswerScore(aJCas);
	    	answerScore.setAnswerScore(score);
	    	answerScore.setLabel(answer.getLabel());
	    	answerScore.setId(answer.getId());
	    	answerScore.setBegin(answer.getBegin());
	    	answerScore.setEnd(answer.getEnd());
	    	answerScore.setScore(1);
	    	answerScore.setComponentId(this.id);
	    	answerScore.addToIndexes();
	    }
	}
}
