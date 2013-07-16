package xml.jaxp;

import javax.xml.parsers.*;
import java.io.*;
import org.w3c.dom.*;

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
public class DomParse
{
	public static void main(String[] args) throws Exception
	{
		//DOM����������
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		//��ȡDOM������
		DocumentBuilder builder = factory.newDocumentBuilder();
		//����XML�ĵ�������ȡ��XML�ĵ���Ӧ��Document
		Document doc = builder.parse(new File("book-list.xml"));
		/*
		������JavaScript�б��ʱ��documentҲ��Document���ʵ��
		��ΪXML�ĵ���Ԫ��ͨ��û��ID���ԣ�
		����Ҳ��ͨ��getElementById()���������ʸ�Ԫ�ء�
		*/
		//��ø�ڵ�ķ�����getDocumentElement
		Element bookList = doc.getDocumentElement();
		//��ȡ��Ԫ���������С�������鼮����Ԫ�أ�
		//�����*��Ϊ����ɻ�ȡ������Ԫ��
		NodeList nodeList = bookList.getElementsByTagName("������鼮");
		//����ÿ����Ԫ��
		for (int i = 0; i < nodeList.getLength() ; i++ )
		{
			System.out.println("------------��" + i + "��������鼮--------------");
		
			Node comBook = nodeList.item(i);
			//��ȡISBN���Խڵ�
			Node isbnAttr = comBook.getAttributes().getNamedItem("ISBN");
			if (isbnAttr != null)
			{
				System.out.println("��ͼ���ISBNΪ��" + isbnAttr.getNodeValue());
			}
			//��ȡ����comBook�µ�������Ԫ��
			NodeList attList = comBook.getChildNodes();
			//����ÿ����Ԫ��
			for (int j = 0; j < attList.getLength() ; j++ )
			{
				System.out.println(attList.item(j).getNodeValue().trim());
			}
		}
		//��ȡ��Ԫ���������С���ѧ�鼮����Ԫ�أ�
		nodeList = bookList.getElementsByTagName("��ѧ�鼮");
		//����ÿ����Ԫ��
		for (int i = 0; i < nodeList.getLength() ; i++ )
		{
			System.out.println("------------��" + i + "����ѧ�鼮--------------");
			Node cultureBook = nodeList.item(i);
			System.out.println(cultureBook.getNodeValue());
		}
		//��ȡ��Ԫ���������С���ѧ�鼮����Ԫ�أ�
		nodeList = bookList.getElementsByTagName("��ѧ�鼮");
		//����ÿ����Ԫ��
		for (int i = 0; i < nodeList.getLength() ; i++ )
		{
			System.out.println("------------��" + i + "����ѧ�鼮--------------");
			Node cultureBook = nodeList.item(i);
			System.out.println(cultureBook.getNodeValue().trim());
			System.out.println(cultureBook.getFirstChild().getNodeValue().trim());
		}
	}
}
