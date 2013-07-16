package xml.dom4j;

import java.io.*;
import org.dom4j.*;
import org.dom4j.io.*;

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
public class Dom4jUpdate
{
	public static void main(String[] args) throws Exception
	{
		//ʹ��SAXReader������XML�ĵ�
		SAXReader  reader = new SAXReader();
		//����Ԫ�صĿ�ʼ��ǩ��������ǩ֮��Ŀհ�
		reader.setStripWhitespaceText(true);
		//��Ԫ��֮�����ڵ��ı����ݺϲ�����
		reader.setMergeAdjacentText(true);
		Document doc = reader.read(new File("book.xml"));
		//��ȡXML�ĵ��ĸ�Ԫ��
		Element root = doc.getRootElement();
		//����һ����������鼮����Ԫ��
		Element pcBook = root.addElement("������鼮");
		//���һ���������Ϊisbn����ֵ
		pcBook.addAttribute("isbn" , Math.round((Math.random() * 1000)) + ""); 
		//Ϊ��������鼮��Ԫ����ӡ���������Ԫ�أ�����������ֵ
		Element name = pcBook.addElement("����");
		name.setText("���Java����");
		//Ϊ��������鼮��Ԫ����ӡ��۸���Ԫ�أ�����������ֵ
		Element price = pcBook.addElement("�۸�");
		price.setText(99.00 + "");
		//����һ�������ʽ����
		OutputFormat format = new OutputFormat("	", true, "GBK"); 
		FileWriter fw = new FileWriter("book.xml");
		//����һ��XMLWriter����
		XMLWriter writer = new XMLWriter(fw , format); 
		writer.write(doc);  
		fw.close();
	}
}
