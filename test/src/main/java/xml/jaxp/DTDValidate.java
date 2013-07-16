package xml.jaxp;

import javax.xml.parsers.*;
import java.io.*;


public class DTDValidate
{
	public static void main(String[] args) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.parse(new File("xmlSource/book.xml"));
	}
}
