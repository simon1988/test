package xml.jdom;

import java.io.IOException;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * @author kingwong
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TestJDOM2 {

	public static void main(String[] args) {
		Element rootElement = new Element("MyInfo");
		Document myDocument = new Document(rootElement);

		// Attribute rootAttri = new Attribute("comment","introduce myself");
		// rootElement.setAttribute(rootAttri);

		rootElement.setAttribute("comment", "introduce myself");
		// rootElement.setAttribute(new
		// Attribute("comment","introduce myself"));
		// Element sexElement = new Element("sex");
		// rootElement.addContent(sexElement);

		// Element nameElement = new Element("name");
		// nameElement.addContent("kingwong");
		// rootElement.addContent(nameElement);

		rootElement.addContent((Content) (new Element("name")
				.addContent("kingwong")));
		rootElement
				.addContent(new Element("sex").setAttribute("value", "male"));
		rootElement.addContent((Content) (new Element("contract")
				.addContent((Content) (new Element("telephone")
						.addContent("87654321")))));

		// rootElement.removeChild("sex");

		XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
		try {
			xmlOut.output(myDocument, System.out);
			// xmlOut.output(rootElement.getChild("name"),System.out);
			// String outString = xmlOut.outputString(myDocument);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
