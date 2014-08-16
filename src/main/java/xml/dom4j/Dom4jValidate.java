package xml.dom4j;

import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;
import java.io.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
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
		//创建启用DTD验证功能的SAXReader对象
		SAXReader reader = new SAXReader(true);
		//启用XML Schema验证XML文档的有效性
		reader.setFeature("http://apache.org/xml/features/validation/schema"
			, true);
//		//为XML文档的设置校验有效性的Schema文档
		reader.setProperty(
			"http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation"
			, "book.xsd"
		);
		//为SAXReader设置ErrorHandler
		reader.setErrorHandler(new DefaultHandler()
		{
			public void error(SAXParseException exception) 
				throws SAXException
			{
				System.out.println("---error方法---");
				System.out.println(exception.getSystemId() + "文档的第"
					+ exception.getLineNumber()
					+ "行，第" + exception.getColumnNumber() 
					+ "发生有效性错误，错误信息是：" + exception.getMessage());
			}
		}); 
		Document doc = reader.read(new File("book.xml"));
	}
}
