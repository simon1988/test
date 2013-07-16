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
public class DomCreate
{
	public static void main(String[] args) throws Exception
	{
		//DOM����������
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		//��ȡDOM������
		DocumentBuilder builder = factory.newDocumentBuilder();
		//����һ���µ�Document����
		Document doc = builder.newDocument();
		//����XML�汾
//		doc.setXmlVersion("1.0");
		//��������ָ��
		ProcessingInstruction pi = doc.createProcessingInstruction("crazyit"
			, "href='http:/www.crazyit.org'");
		//��Ӵ���ָ��
		doc.appendChild(pi);
		Comment comment = doc.createComment("��Ԫ��֮ǰ��ע��");
		doc.appendChild(comment);
		Element root = doc.createElement("student");
		//Ϊ��Ԫ�����ѧ������
		root.setAttribute("ѧ��" , "1992121232");;
		//����nameԪ��
		Element item = doc.createElement("name"); 
		//ΪnameԪ�������ı��ӽڵ�
		item.appendChild(doc.createTextNode("����") );
		//��nameԪ����ӵ���Ԫ����
		root.appendChild(item);
		//����ageԪ��
		item = doc.createElement("age"); 
		//ΪageԪ�������ı���Ԫ��
		item.appendChild(doc.createTextNode("28" ));
		//��ageԪ����ӵ���Ԫ����
		root.appendChild(item);
		//����highԪ��
		item = doc.createElement("high");
		//ΪhighԪ������ı���Ԫ��
		item.appendChild(doc.createTextNode("1.72" ));
		//��highԪ����ӵ���Ԫ����
		root.appendChild(item);
		//����scoreԪ��
		item = doc.createElement("score");
		//����JavaԪ��
		Element lesson = doc.createElement("Java");
		//ΪJavaԪ������ı���Ԫ�ء�
		lesson.appendChild(doc.createTextNode("95"));
		//��JavaԪ����ӵ�scoreԪ��
		item.appendChild(lesson);
		//����StrutsԪ��
		lesson = doc.createElement("Struts");
		//ΪStrutsԪ������ı���Ԫ�ء�
		lesson.appendChild(doc.createTextNode("90"));
		//��StrutsԪ����ӵ�scoreԪ��
		item.appendChild(lesson);
		//����HibernateԪ��
		lesson = doc.createElement("Hibernate");
		//ΪHibernateԪ������ı���Ԫ�ء�
		lesson.appendChild(doc.createTextNode("90"));
		//��HibernateԪ����ӵ�scoreԪ��
		item.appendChild(lesson);
		//��scoreԪ����ӵ���Ԫ����
		root.appendChild(item); 
		//Ϊ�ĵ�ָ����Ԫ��
		doc.appendChild(root);

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
		FileWriter stringOut = new FileWriter("new.xml");
		out.setCharacterStream(stringOut);
		//ִ�����л���Ҳ���ǽ�DOM��ת����XML�ĵ���
		serializer.write(doc , out);
	}
}
