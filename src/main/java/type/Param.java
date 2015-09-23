

/* First created by JCasGen Mon Sep 21 16:42:50 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Mon Sep 21 23:07:45 EDT 2015
 * XML source: /Users/zhuyund/Documents/workspace/pi3-zhuyund/src/main/resources/typeSystem.xml
 * @generated */
public class Param extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Param.class);
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
  protected Param() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Param(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Param(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Param(JCas jcas, int begin, int end) {
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
  //* Feature: outputDir

  /** getter for outputDir - gets output dir
   * @generated
   * @return value of the feature 
   */
  public String getOutputDir() {
    if (Param_Type.featOkTst && ((Param_Type)jcasType).casFeat_outputDir == null)
      jcasType.jcas.throwFeatMissing("outputDir", "type.Param");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Param_Type)jcasType).casFeatCode_outputDir);}
    
  /** setter for outputDir - sets output dir 
   * @generated
   * @param v value to set into the feature 
   */
  public void setOutputDir(String v) {
    if (Param_Type.featOkTst && ((Param_Type)jcasType).casFeat_outputDir == null)
      jcasType.jcas.throwFeatMissing("outputDir", "type.Param");
    jcasType.ll_cas.ll_setStringValue(addr, ((Param_Type)jcasType).casFeatCode_outputDir, v);}    
   
    
  //*--------------*
  //* Feature: n

  /** getter for n - gets n for n-gram configuration
   * @generated
   * @return value of the feature 
   */
  public int getN() {
    if (Param_Type.featOkTst && ((Param_Type)jcasType).casFeat_n == null)
      jcasType.jcas.throwFeatMissing("n", "type.Param");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Param_Type)jcasType).casFeatCode_n);}
    
  /** setter for n - sets n for n-gram configuration 
   * @generated
   * @param v value to set into the feature 
   */
  public void setN(int v) {
    if (Param_Type.featOkTst && ((Param_Type)jcasType).casFeat_n == null)
      jcasType.jcas.throwFeatMissing("n", "type.Param");
    jcasType.ll_cas.ll_setIntValue(addr, ((Param_Type)jcasType).casFeatCode_n, v);}    
   
    
  //*--------------*
  //* Feature: documentName

  /** getter for documentName - gets input document name
   * @generated
   * @return value of the feature 
   */
  public String getDocumentName() {
    if (Param_Type.featOkTst && ((Param_Type)jcasType).casFeat_documentName == null)
      jcasType.jcas.throwFeatMissing("documentName", "type.Param");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Param_Type)jcasType).casFeatCode_documentName);}
    
  /** setter for documentName - sets input document name 
   * @generated
   * @param v value to set into the feature 
   */
  public void setDocumentName(String v) {
    if (Param_Type.featOkTst && ((Param_Type)jcasType).casFeat_documentName == null)
      jcasType.jcas.throwFeatMissing("documentName", "type.Param");
    jcasType.ll_cas.ll_setStringValue(addr, ((Param_Type)jcasType).casFeatCode_documentName, v);}    
  }

    