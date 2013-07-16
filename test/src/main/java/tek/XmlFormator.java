package tek;

import java.io.File;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlFormator {
	
	private static final String FILE_NAME = "C:\\Users\\xniu1\\workspaceIris\\codes\\server\\irisReports\\adf\\fmgen\\cat.xml";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		SAXReader reader = new SAXReader();
		reader.setStripWhitespaceText(true);
		reader.setMergeAdjacentText(true);
		Document doc = reader.read(new File(FILE_NAME));
		OutputFormat format = new OutputFormat("	", true, "UTF-8");
		FileWriter fw = new FileWriter(FILE_NAME.replace(".xml", ".formated.xml"));
		XMLWriter writer = new XMLWriter(fw, format);
		writer.write(doc);
		fw.close();
	}

}
