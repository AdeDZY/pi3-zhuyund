

/* First created by JCasGen Mon Sep 21 11:05:26 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Answer Score
 * Updated by JCasGen Mon Sep 21 23:07:45 EDT 2015
 * XML source: /Users/zhuyund/Documents/workspace/pi3-zhuyund/src/main/resources/typeSystem.xml
 * @generated */
public class AnswerScore extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(AnswerScore.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected AnswerScore() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public AnswerScore(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public AnswerScore(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public AnswerScore(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: answerScore

  /** getter for answerScore - gets the score of the answer
   * @generated
   * @return value of the feature 
   */
  public float getAnswerScore() {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_answerScore == null)
      jcasType.jcas.throwFeatMissing("answerScore", "type.AnswerScore");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_answerScore);}
    
  /** setter for answerScore - sets the score of the answer 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswerScore(float v) {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_answerScore == null)
      jcasType.jcas.throwFeatMissing("answerScore", "type.AnswerScore");
    jcasType.ll_cas.ll_setFloatValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_answerScore, v);}    
   
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets the answer's id
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "type.AnswerScore");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets the answer's id 
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "type.AnswerScore");
    jcasType.ll_cas.ll_setStringValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: label

  /** getter for label - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getLabel() {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "type.AnswerScore");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLabel(boolean v) {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "type.AnswerScore");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_label, v);}    
  }

    