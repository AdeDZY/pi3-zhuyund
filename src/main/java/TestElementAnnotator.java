import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;

import type.Answer;
import type.Question;
import type.InputDocument;
import type.ComponentAnnotation;

/**
 * Annotate questions and answers
 * @author zhuyund
 *
 */
public class TestElementAnnotator extends JCasAnnotator_ImplBase {

	public final String id = "TestElementAnnotator";
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// get document text
	    String docText = aJCas.getDocumentText();
	    	    
	    // Set Question
	    int startLine = 0;
	    int endLine = docText.indexOf("\n");
	    Question question = new Question(aJCas);
	    question.setBegin(startLine);
	    question.setEnd(endLine);
	    question.setScore(1);
	    question.setComponentId(this.id);

	    question.setSentence(docText.substring(1, endLine).trim());
	    question.addToIndexes();
	    	    
	    // Set Answers
	    int tmp = 0;
	    List<ComponentAnnotation> answers = new ArrayList<ComponentAnnotation>();
	    while(true){
	    	startLine = endLine + 1;
	    	if(startLine >= docText.length())
	    		break;
	    	endLine = docText.indexOf("\n", startLine);
	    	
	    	Answer answer = new Answer(aJCas);
	    	answer.setBegin(startLine);
	    	answer.setEnd(endLine);
	    	answer.setScore(1);
	    	answer.setComponentId(this.id);
	    	
	    	// Find id, label and sentence of an answer
	    	// An answer looks like:
	    	// A1 1 John loves Mary with all his heart.
	    	tmp = docText.indexOf(" ", startLine);
	    	answer.setId(docText.substring(startLine, tmp)); // e.g. "A0"
	    	tmp = docText.indexOf(" ", tmp + 1);
	    	answer.setLabel(docText.substring(tmp - 1, tmp).equals("1"));
	    	answer.setSentence(docText.substring(tmp + 1, endLine).trim());
	    	answer.addToIndexes();
	    	
	    	answers.add(answer);
	    }
	    
	    
	    // Set Document 
	    InputDocument document = new InputDocument(aJCas);
	    document.setBegin(0);
	    document.setEnd(docText.length() - 1);
	    document.setComponentId(this.id);
	    document.setScore(1);
	    
	    document.setQuestion(question);
	    document.setAnswers(FSListCreator.createFSList(aJCas, answers));
	    document.addToIndexes();

	}

}
