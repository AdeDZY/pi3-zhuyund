/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import type.Answer;
import type.Question;
import type.Token;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.process.PTBTokenizer.PTBTokenizerFactory;
import edu.stanford.nlp.process.Tokenizer;

/**
 * Annotate tokens
 * Using stanford nlp toolkit
 * @author zhuyund
 *
 */
public class TokenAnnotator extends JCasAnnotator_ImplBase {

	public final String id = "TokenAnnotator";
	
  @Override
  public void process(JCas aJCas) {
    // get annotation indexes
    FSIndex answerIndex = aJCas.getAnnotationIndex(Answer.type);
    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
    String docText = aJCas.getDocumentText();
    
    TokenizerFactory<Word> factory = PTBTokenizerFactory.newTokenizerFactory();
    
    // tokenize the question
    Iterator questionIter = questionIndex.iterator();
    while (questionIter.hasNext()) {
      Question question = (Question) questionIter.next();
      String sentence = question.getSentence();
      int offset =  docText.indexOf(sentence);
      Tokenizer<Word> tokenizer = factory.getTokenizer(new StringReader(sentence));
      List<Word> tokens = tokenizer.tokenize();
      //String[] tokens = sentence.split(" ");
      int s = 0;
      int e = -1;
      for(Word token : tokens){
    	  Token tokenAnnotation = new Token(aJCas);
    	  s = sentence.indexOf(token.toString(), e);	
    	  e = s + token.toString().length();
    	  tokenAnnotation.setBegin(s + offset);
    	  tokenAnnotation.setEnd(e + offset);
    	  tokenAnnotation.setComponentId(this.id);
    	  tokenAnnotation.setScore(1);
    	  tokenAnnotation.addToIndexes();
      }      
      }
    
    // tokenize answers
    Iterator answerIter = answerIndex.iterator();
    while (answerIter.hasNext()) {
      Answer answer = (Answer) answerIter.next();
      String sentence = answer.getSentence();
      int offset =  docText.indexOf(sentence, answer.getBegin());
      Tokenizer<Word> tokenizer = factory.getTokenizer(new StringReader(sentence));
      List<Word> tokens = tokenizer.tokenize();
      //String[] tokens = sentence.split(" ");
      int s = 0;
      int e = -1;
      for(Word token : tokens){
    	  Token tokenAnnotation = new Token(aJCas);
    	  s = sentence.indexOf(token.toString(), e);	
    	  e = s + token.toString().length();
    	  tokenAnnotation.setBegin(s + offset);
    	  tokenAnnotation.setEnd(e + offset);
    	  tokenAnnotation.setComponentId(this.id);
    	  tokenAnnotation.setScore(1);
    	  tokenAnnotation.addToIndexes();
      }      
      }
  }
  
  private void tokennizeAndAddToIndexes(String sentence, int offset, JCas aJCas){
	  //String[]
	  // TODO tokenize
  }

}
