package xml.jdom;

import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Testjdom {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SAXBuilder sb=new SAXBuilder();
		try{
			Document doc=sb.build("xmlSource/link.xml");
			Element root=doc.getRootElement();
			List<Element> list=root.getChildren();
			Element link1=list.get(0);
			Element link2=list.get(1);
			System.out.println(link1.getChild("url").getText());
			System.out.println(link2.getChild("url").getAttributeValue("newWindow"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
