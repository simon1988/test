package xml.jaxp;

import javax.xml.parsers.*;
import java.io.*;
import org.w3c.dom.*;
import org.w3c.dom.ls.*;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

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
public class DomUpdate
{
	public static void main(String[] args) throws Exception
	{
		//DOM����������
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		//��ȡDOM������
		DocumentBuilder builder = factory.newDocumentBuilder();
		//����һ���µ�Document����
		Document doc = builder.parse("new.xml");
		//��ȡ�ĵ������б�ǩ��Ϊ��name����Ԫ��
		NodeList names = doc.getElementsByTagName("name");
		//���names�ڵ��б?Ϊnull�������ٰ�1���ڵ�
		if (names != null && names.getLength() > 0)
		{
			//��ȡ��һ���ڵ�
			Node name = names.item(0);
			//�޸Ľڵ����
			name.setNodeValue("�����" ); 
		}
		//���DOMImplementationRegistry�������ǻ�ȡDOMImplementation�Ĺ���
		DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
		//��ȡDOMImplementationLS����
		DOMImplementationLS domImplLS = (DOMImplementationLS)registry
			.getDOMImplementation("LS");
		//��ȡLSSerializer��������ר���������л�DOM���ĵĹ���
		LSSerializer serializer = domImplLS.createLSSerializer();
		//����ʹ�ú�������ʹ��XML�ĵ�������
		serializer.getDomConfig().setParameter("format-pretty-print" , true); 
		LSOutput out = domImplLS.createLSOutput();
		//ָ������ĵ��������õ��ַ�
		out.setEncoding("GB2312"); 
		FileWriter stringOut = new FileWriter("update.xml");
		out.setCharacterStream(stringOut);
		//ִ�����л���Ҳ���ǽ�DOM��ת����XML�ĵ���
		serializer.write(doc , out);
	}
}
