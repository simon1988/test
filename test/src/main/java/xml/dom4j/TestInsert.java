package xml.dom4j;

import java.io.FileWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class TestInsert {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read("xmlSource/books.xml");
		Element it = (Element)doc.selectSingleNode("//computer_book");
		List<Element> list = it.selectNodes("./book_name");
		it.elements().add(list.get(0).createCopy());
		FileWriter fw =new FileWriter("xmlSource/books.xml");
		XMLWriter writer = new XMLWriter(fw); 
		writer.write(doc);
		fw.close();
	}

}
