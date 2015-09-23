import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import type.AnswerScore;
import type.Param;

/**
 * Evaluate the system and write to output file
 * @author zhuyund
 *
 */
public class QaCasConsumer extends CasConsumer_ImplBase {

	private int mDocNum;
	private File mOutputDir;
	public static final String PARAM_OUTPUTDIR = "OutputDirectory";

	@Override
	public void initialize() throws ResourceInitializationException {
		mDocNum = 0;
		mOutputDir = new File((String) getConfigParameterValue(PARAM_OUTPUTDIR));
		if (!mOutputDir.exists()) {
			mOutputDir.mkdirs();
		}
	}

	public void processCas(CAS aCAS) throws ResourceProcessException {
		JCas jcas;
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}

		// get all answer scores and sort
		int N = 0; // # of right answers
		FSIndex answerScoreIndex =  jcas.getAnnotationIndex(AnswerScore.type);
		Iterator answerScoreIt = answerScoreIndex.iterator();
		ArrayList<MemAnswerScore> scores = new ArrayList<MemAnswerScore>();
		while(answerScoreIt.hasNext()){
			AnswerScore answerScore = (AnswerScore)answerScoreIt.next();
			MemAnswerScore mScore = new MemAnswerScore();
			mScore.id = answerScore.getId();
			mScore.score = answerScore.getAnswerScore();
			mScore.label = answerScore.getLabel();
			scores.add(mScore);
			
			if(mScore.label)
				N += 1;
		}
		Collections.sort(scores);
		float nTruePos = 0;
		for(int i = 0; i < N; i++){
			if(scores.get(i).label)
				nTruePos += 1;
		}
		float prec = 0;
		if(N > 0)
			prec = nTruePos/N;
	
		
		// get output file path
		Iterator it = jcas.getAnnotationIndex(Param.type).iterator();
		File outFile = null;
		Param param = (Param)it.next();
		String outFileName = "a" + param.getDocumentName().substring(1);
		
		// mkdir if outputdir not exists
		mOutputDir = new File(param.getOutputDir());
		if (!mOutputDir.exists()) {
			mOutputDir.mkdirs();
		}
		outFile = new File(param.getOutputDir(), outFileName);

		//  calulate the prec@N and write to output file
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(outFile));
			writer.write(prec + "\n");
			for(MemAnswerScore mScore: scores){
				writer.write(mScore.id + " " + mScore.score + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
