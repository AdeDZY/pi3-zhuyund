import java.io.IOException;

import org.apache.uima.UIMAFramework;
import org.apache.uima.collection.CollectionProcessingEngine;
import org.apache.uima.collection.base_cpm.CasProcessor;
import org.apache.uima.collection.metadata.CpeDescription;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

public class Main {

	/**
	 * This method is the main program and entry point of your system for PI3.
	 * It runs a Collection Processing Engine (CPE).
	 * 
	 * @param args
	 * @throws IOException
	 * @throws InvalidXMLException
	 * @throws ResourceInitializationException
	 * @throws ResourceConfigurationException
	 */
	public static void main(String[] args) throws InvalidXMLException,
			IOException, ResourceInitializationException,
			ResourceConfigurationException {
		// parse CPE descriptor in file specified on command line
		String cpeDescPath = "src/main/resources/cpeDescriptor.xml";
		CpeDescription cpeDesc = UIMAFramework.getXMLParser()
				.parseCpeDescription(new XMLInputSource(cpeDescPath));

		// instantiate CPE
		CollectionProcessingEngine mCPE = UIMAFramework
				.produceCollectionProcessingEngine(cpeDesc);

		// Create and register a Status Callback Listener
		// mCPE.addStatusCallbackListener(new StatusCallbackListenerImpl());

		QaCollectionReader reader = (QaCollectionReader) mCPE
				.getCollectionReader();
		reader.initialize(args[1], Integer.parseInt(args[0]), args[2]);
		// reader.reconfigure();

		// Start Processing
		mCPE.process();

	}

}
