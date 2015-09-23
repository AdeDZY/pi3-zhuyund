import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import type.Param;


public class QaCollectionReader extends CollectionReader_ImplBase {
  /**
   * Name of configuration parameter that must be set to the path of a directory containing input
   * files.
   */
  public static final String PARAM_INPUTDIR = "InputDirectory";

  private ArrayList<File> mFiles;	// files in the inputDir

  private int mCurrentIndex;	// current file index
  
  private int n;
  private String outputDir;

  @Override
  public void initialize() throws ResourceInitializationException {
    File directory = new File(((String) getConfigParameterValue(PARAM_INPUTDIR)).trim());
    mCurrentIndex = 0;

    // if input directory does not exist or is not a directory, throw exception
    if (!directory.exists() || !directory.isDirectory()) {
      throw new ResourceInitializationException(ResourceConfigurationException.DIRECTORY_NOT_FOUND,
              new Object[] { PARAM_INPUTDIR, this.getMetaData().getName(), directory.getPath() });
    }
    
    // get list of files in the specified directory
    mFiles = new ArrayList<File>();
    addFilesFromDir(directory);
  }
  
  public void initialize(String inputDir, int n, String outputDir) throws ResourceInitializationException {
	    File directory = new File(inputDir);
	    mCurrentIndex = 0;

	    // if input directory does not exist or is not a directory, throw exception
	    if (!directory.exists() || !directory.isDirectory()) {
	      throw new ResourceInitializationException(ResourceConfigurationException.DIRECTORY_NOT_FOUND,
	              new Object[] { PARAM_INPUTDIR, this.getMetaData().getName(), directory.getPath() });
	    }
	    
	    // get list of files in the specified directory
	    mFiles = new ArrayList<File>();
	    addFilesFromDir(directory);
	    this.n = n;
	    this.outputDir = outputDir;
	  }
  
  /**
   * This method adds files in the directory passed in as a parameter to mFiles.
   * @param dir
   */
  private void addFilesFromDir(File dir) {
    File[] files = dir.listFiles();
    for (int i = 0; i < files.length; i++) {
      if (!files[i].isDirectory()) {
        mFiles.add(files[i]);
      }
    }
  }

  /**
   * @see org.apache.uima.collection.CollectionReader#hasNext()
   */
  public boolean hasNext() {
    return mCurrentIndex < mFiles.size();
  }

  /**
   * @see org.apache.uima.collection.CollectionReader#getNext(org.apache.uima.cas.CAS)
   */
  public void getNext(CAS aCAS) throws IOException, CollectionException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new CollectionException(e);
    }

    // open input stream to file
    File file = (File) mFiles.get(mCurrentIndex);
    mCurrentIndex += 1;
    String text = FileUtils.file2String(file);
      // put document into CAS
    jcas.setDocumentText(text);
    
    // put metadata into CAS
    Param param = new Param(jcas);
    param.setOutputDir(this.outputDir);
    param.setDocumentName(file.getName());
    param.setN(this.n);
    param.addToIndexes();
  }

  /**
   * @see org.apache.uima.collection.base_cpm.BaseCollectionReader#close()
   */
  public void close() throws IOException {
  }

  /**
   * @see org.apache.uima.collection.base_cpm.BaseCollectionReader#getProgress()
   */
  public Progress[] getProgress() {
    return new Progress[] { new ProgressImpl(mCurrentIndex, mFiles.size(), Progress.ENTITIES) };
  }

  /**
   * Gets the total number of documents that will be returned by this collection reader. This is not
   * part of the general collection reader interface.
   * 
   * @return the number of documents in the collection
   */
  public int getNumberOfDocuments() {
    return mFiles.size();
  }

}
