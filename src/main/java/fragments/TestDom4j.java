package fragments;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
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
	public static void main(String[] args) {
		TestDom4j instance = new TestDom4j();
		instance.write();
		instance.update();
		instance.read();
	}

}
