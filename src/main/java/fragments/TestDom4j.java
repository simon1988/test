package fragments;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class TestDom4j {

	public void write(){
		try{
			DocumentFactory factory = new DocumentFactory();
			Document doc = factory.createDocument();
			Element root = doc.addElement("books");
			for (int i = 0; i < 4 ; i++ ){
				Element pcBook = root.addElement("book");
				pcBook.addAttribute("isbn" , Math.round((Math.random() * 1000)) + ""); 
				Element name = pcBook.addElement("name");
				name.setText("book" + i);
				Element price = pcBook.addElement("price");
				price.setText((i+1) + "");
			}
			OutputFormat format = new OutputFormat("	", true, "UTF-8"); 
			FileWriter fw = new FileWriter("book.xml");
			XMLWriter writer = new XMLWriter(fw , format); 
			writer.write(doc);  
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void update(){
		try{
			SAXReader  reader = new SAXReader();
			reader.setStripWhitespaceText(true);
			reader.setMergeAdjacentText(true);
			Document doc = reader.read(new File("book.xml"));
			Element root = doc.getRootElement();
			Element pcBook = root.addElement("newbook");
			pcBook.addAttribute("isbn" , Math.round((Math.random() * 1000)) + ""); 
			Element name = pcBook.addElement("name");
			name.setText("thinking in java");
			Element price = pcBook.addElement("price");
			price.setText(99.00 + "");

			OutputFormat format = new OutputFormat("	", true, "UTF-8"); 
			FileWriter fw = new FileWriter("book.xml");
			XMLWriter writer = new XMLWriter(fw , format); 
			writer.write(doc);  
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void read(){
		try{
			SAXReader  reader = new SAXReader();
			Document doc = reader.read(new File("book.xml"));
			Element root = doc.getRootElement();
			List<Element> books = root.selectNodes("//book");
			for(Element book:books){
				System.out.println(book.elementText("name")+"\t"+book.elementText("price")+"\t"+book.attributeValue("isbn"));
			}
			Element book = root.element("newbook");
			System.out.println(book.elementText("name")+"\t"+book.elementText("price")+"\t"+book.attributeValue("isbn"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*    
	We can process each <ROW> at a time, there is no need to keep all of them in memory at once. dom4j provides a Event Based Mode for this purpose. 
	We can register an event handler for one or more path expressions. These handlers will then be called on the start and end of each path registered against a particular handler.
	When the start tag of a path is found, the onStart method of the handler registered to the path is called. When the end tag of a path if found, the onEnd method of the handler registered to that path is called.
	
	The onStart and onEnd methods are passed an instance of an ElementPath, which can be used to retrieve the current Element for the given path. 
	If the handler wishes to "prune" the tree being built in order to save memory use, it can simply call the detach() method of the current Element being processed in the handlers onEnd() method.
	 */
	public void processBigXml() throws Exception{
		// enable pruning mode to call me back as each ROW is complete
		SAXReader reader = new SAXReader();
		reader.addHandler( "/ROWSET/ROW", 
		    new ElementHandler() {
		        public void onStart(ElementPath path) {
		        	
		        }
				public void onEnd(ElementPath path) {
		            // process a ROW element
		            Element row = path.getCurrent();
		            Element rowSet = row.getParent();
		            Document document = row.getDocument();
		            // do something here
		            row.detach();
		        }
		    }
		);

		Document document = reader.read(new File("book.xml"));
		// all done
	}
	public static void main(String[] args) {
		TestDom4j instance = new TestDom4j();
		instance.write();
		instance.update();
		instance.read();
	}

}
