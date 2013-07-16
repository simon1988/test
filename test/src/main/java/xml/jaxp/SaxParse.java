package xml.jaxp;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;

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
public class SaxParse
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("--------下面使用XMLReader解析--------");
		//创建XMLReader解析器
		XMLReader reader = XMLReaderFactory.createXMLReader();
		//注册ContentHandler监听器
		reader.setContentHandler(new CrazyItHandler());
		//注册ContentHandler监听器
		reader.setDTDHandler(new CrazyItHandler());
		//开始解析文档
		reader.parse("book.xml");
		System.out.println("--------下面使用SAXParser解析--------");
		//创建SAX解析器工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//创建新的SAX解析器实例
		SAXParser parser = factory.newSAXParser();
		//开始解析XML文档
		parser.parse("book.xml" , new CrazyItHandler()); 
	}
}
