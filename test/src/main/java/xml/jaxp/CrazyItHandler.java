package xml.jaxp;

import org.xml.sax.helpers.*;
import org.xml.sax.*;
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
public class CrazyItHandler extends DefaultHandler
{
	//����һ����Ա���������浱ǰ���ڴ����Tag
	private String currentTag;
	//ÿ�������ı����ݽ������÷���
	public void characters(char[] ch, int start, int length) 
	{
		String content = new String(ch , start , length);
		if (content.trim().length() > 0)
		{
			System.out.println("<" + currentTag + ">Ԫ�ص�ֵ�ǣ�"
				+ content.trim());
		}
	}
	//�����ĵ�����ʱ�����÷�����
	public void endDocument()
	{
		System.out.println("�����ĵ�����");
	}
	//����Ԫ�ؽ���ʱ�����÷�����
	public void endElement(String uri , String localName
		, String qName) 
	{
		System.out.println("����Ԫ�ؽ���:" + qName);
	}
	//����Ԫ���������ռ����Խ���ʱ�����÷���
	public void endPrefixMapping(String prefix) 
	{
		System.out.println("<" + currentTag + ">Ԫ�ص������ռ����Ե�ǰ׺��"
			+ prefix);
	}
	//����Ԫ�������пɺ��ԵĿհ�ʱ�����÷�����
	public void ignorableWhitespace(char[] ch, int start, int length) 
	{
	}
	//��������ָ��ʱ�����÷�����
	public void processingInstruction(String target , String data) 
	{
		System.out.println("��ǰ����ָ���ǣ�" + target);
		System.out.println("����ָ������Ϊ��" + data);
	}
	//����ʵ��ʱ�����÷���
	public void skippedEntity(String name) 
	{
		System.out.println("SAX������������ʵ����Ϊ��" + name);
	}
	//��ʼ����XML�ĵ�ʱ�����÷���
	public void startDocument() 
	{
		System.out.println("��ʼ�����ĵ�");
	}
	//��ʼ����Ԫ��ʱ�����÷�����
	public void startElement(String uri , String localName
		, String qName , Attributes atts) 
	{
		System.out.println("��ʼ����Ԫ��:" + qName);
		currentTag = qName;
		//attrs�����Ԫ�ذ�������������
		int len = atts.getLength();
		if (len > 0)
		{
			System.out.println("<" + currentTag + ">Ԫ�ص���������:" );
			for (int i = 0; i < len ; i++ )
			{
				System.out.println(atts.getQName(i) + "--->"
					+ atts.getValue(i));
			}
		}
	}
	//��ʼ����Ԫ���������ռ�����ʱ�����÷���
	public void startPrefixMapping(String prefix, String uri)
	{
		System.out.println("<" + currentTag + ">Ԫ�ص������ռ����Ե�ǰ׺��"
			+ prefix);
		System.out.println("<" + currentTag + ">Ԫ�ص������ռ����Ե�URIΪ��"
			+ uri);
	}
	//������DTD�еķ��Ŷ���ʱ�����÷���
	public void notationDecl(String name , String publicId
		 , String systemId)throws SAXException
	{
		System.out.println("��ǰ���ŵ������ǣ�" + name); 
		System.out.println("��ǰ���ŵ�PUBLIC URI��" + publicId); 
		System.out.println("��ǰ���ŵ�SYSTEM URI��" + systemId); 
	}
	//������DTD�е�δ����ʵ��ʱ�����÷���
	public void unparsedEntityDecl(String name , String publicId
		, String systemId , String notationName)throws SAXException
	{
		System.out.println("��ǰδ����ʵ��������ǣ�" + name); 
		System.out.println("��ǰδ����ʵ���PUBLIC URI��" + publicId); 
		System.out.println("��ǰδ����ʵ���SYSTEM URI��" + systemId);
		System.out.println("��ǰδ����ʵ������ķ�������" + notationName);
	}
}
