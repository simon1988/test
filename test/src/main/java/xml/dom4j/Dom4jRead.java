package xml.dom4j;

import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;
import java.io.*;

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
public class Dom4jRead
{
	public static void main(String[] args) throws Exception
	{
		//ʹ��XPP3Reader������XML�ĵ�
//		XPP3Reader reader = new XPP3Reader();
//		//ʹ��SAXReader������XML�ĵ�
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("book.xml"));
		Element root = doc.getRootElement();
		parse(root);
	}
	public static void parse(Element ele)
	{
		//����ǰԪ�ذ�������������
		parseAttribute(ele);
		//��ȡ��ǰԪ�ذ�����������Ԫ��
		List el = ele.elements(); 
		//����ÿ����Ԫ��
		for (Object e : el)
		{
			Element element = (Element)e;
			//�����Ԫ�ص����ݲ���ֻ�����ַ���
			if (!element.isTextOnly())
			{
				parse(element);
			}
			else
			{
				//����ǰԪ�ص�ȫ������
				parseAttribute(element);
				//��ȡ��ǰԪ�ص�����
				System.out.println(element.getQName().getName() 
					+ "--->" + element.getText());
			}
		}
	}
	//����һ����������ָ��Ԫ�ص���������
	public static void parseAttribute(Element ele)
	{
		//��ȡElementԪ�ص���������
		List attList = ele.attributes();
		//����ElementԪ��ÿ������ 
		for (Object e : attList)
		{
			Attribute attr = (Attribute)e;
			//���ʵ�ǰԪ�ص�ÿ�����Ե�����ֵ
			System.out.println(ele.getQName().getName() + "Ԫ�ص�"
				+ attr.getQName().getName() + "����ֵΪ��" 
				+ attr.getValue());
		}
	}
}
