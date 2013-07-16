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
public class VisitorRead
{
	public static void main(String[] args) throws Exception
	{
		//ʹ��XPP3Reader������XML�ĵ�
		//XPP3Reader reader = new XPP3Reader();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("book.xml"));
		//ʹ�÷�����ģʽ������XML�ĵ���
		doc.accept(new YeekuVistor());
	}
}
//����һ��Visitorʵ���ࡣ
class YeekuVistor extends VisitorSupport
{
	//���浱ǰ���ڴ���Ľڵ�
	private String currentElement;
	//��Visitor����Ԫ��ʱ�ص��÷���
	public void visit(Element node)
	{
		//����ڵ�����ȫ�����ı�
		if (node.isTextOnly())
		{
			System.out.println(node.getName() + "Ԫ�ص�ֵ�ǣ�"
				+ node.getText());
		}
		currentElement = node.getName();
	}
	//��Visitor��������ʱ�ص��÷���
	public void visit(Attribute node)
	{
		System.out.println(currentElement + "Ԫ�ص�"
			+ node.getName() + "���Ե�ֵ�ǣ�"
			+ node.getText());
	}
	//��Visitor���ʴ���ָ��ʱ�ص��÷���
	public void visit(ProcessingInstruction node)
	{
		System.out.println("����ָ��"
			+ node.getTarget() + "�������ǣ�"
			+ node.getText());
	}
}