package xml.jaxp;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;

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
public class SaxParse
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("--------����ʹ��XMLReader����--------");
		//����XMLReader������
		XMLReader reader = XMLReaderFactory.createXMLReader();
		//ע��ContentHandler������
		reader.setContentHandler(new CrazyItHandler());
		//ע��ContentHandler������
		reader.setDTDHandler(new CrazyItHandler());
		//��ʼ�����ĵ�
		reader.parse("book.xml");
		System.out.println("--------����ʹ��SAXParser����--------");
		//����SAX����������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//�����µ�SAX������ʵ��
		SAXParser parser = factory.newSAXParser();
		//��ʼ����XML�ĵ�
		parser.parse("book.xml" , new CrazyItHandler()); 
	}
}
