
/* First created by JCasGen Mon Sep 21 16:42:50 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Mon Sep 21 23:07:45 EDT 2015
 * @generated */
public class Param_Type extends ComponentAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Param_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Param_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Param(addr, Param_Type.this);
  			   Param_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Param(addr, Param_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Param.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("type.Param");
 
  /** @generated */
  final Feature casFeat_outputDir;
  /** @generated */
  final int     casFeatCode_outputDir;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getOutputDir(int addr) {
        if (featOkTst && casFeat_outputDir == null)
      jcas.throwFeatMissing("outputDir", "type.Param");
    return ll_cas.ll_getStringValue(addr, casFeatCode_outputDir);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setOutputDir(int addr, String v) {
        if (featOkTst && casFeat_outputDir == null)
      jcas.throwFeatMissing("outputDir", "type.Param");
    ll_cas.ll_setStringValue(addr, casFeatCode_outputDir, v);}
    
  
 
  /** @generated */
  final Feature casFeat_n;
  /** @generated */
  final int     casFeatCode_n;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getN(int addr) {
        if (featOkTst && casFeat_n == null)
      jcas.throwFeatMissing("n", "type.Param");
    return ll_cas.ll_getIntValue(addr, casFeatCode_n);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setN(int addr, int v) {
        if (featOkTst && casFeat_n == null)
      jcas.throwFeatMissing("n", "type.Param");
    ll_cas.ll_setIntValue(addr, casFeatCode_n, v);}
    
  
 
  /** @generated */
  final Feature casFeat_documentName;
  /** @generated */
  final int     casFeatCode_documentName;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDocumentName(int addr) {
        if (featOkTst && casFeat_documentName == null)
      jcas.throwFeatMissing("documentName", "type.Param");
    return ll_cas.ll_getStringValue(addr, casFeatCode_documentName);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDocumentName(int addr, String v) {
        if (featOkTst && casFeat_documentName == null)
      jcas.throwFeatMissing("documentName", "type.Param");
    ll_cas.ll_setStringValue(addr, casFeatCode_documentName, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Param_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_outputDir = jcas.getRequiredFeatureDE(casType, "outputDir", "uima.cas.String", featOkTst);
    casFeatCode_outputDir  = (null == casFeat_outputDir) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_outputDir).getCode();

 
    casFeat_n = jcas.getRequiredFeatureDE(casType, "n", "uima.cas.Integer", featOkTst);
    casFeatCode_n  = (null == casFeat_n) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_n).getCode();

 
    casFeat_documentName = jcas.getRequiredFeatureDE(casType, "documentName", "uima.cas.String", featOkTst);
    casFeatCode_documentName  = (null == casFeat_documentName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_documentName).getCode();

  }
}



    