package xml.dom4j;

import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;
import java.io.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class Dom4jValidate
{
	public static void main(String[] args) throws Exception
	{
		//��������DTD��֤���ܵ�SAXReader����
		SAXReader reader = new SAXReader(true);
		//����XML Schema��֤XML�ĵ�����Ч��
		reader.setFeature("http://apache.org/xml/features/validation/schema"
			, true);
//		//ΪXML�ĵ�������У����Ч�Ե�Schema�ĵ�
		reader.setProperty(
			"http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation"
			, "book.xsd"
		);
		//ΪSAXReader����ErrorHandler
		reader.setErrorHandler(new DefaultHandler()
		{
			public void error(SAXParseException exception) 
				throws SAXException
			{
				System.out.println("---error����---");
				System.out.println(exception.getSystemId() + "�ĵ��ĵ�"
					+ exception.getLineNumber()
					+ "�У���" + exception.getColumnNumber() 
					+ "������Ч�Դ��󣬴�����Ϣ�ǣ�" + exception.getMessage());
			}
		}); 
		Document doc = reader.read(new File("book.xml"));
	}
}
