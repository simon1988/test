package xml.dom4j;

import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;
import java.io.*;

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
public class VisitorRead
{
	public static void main(String[] args) throws Exception
	{
		//使用XPP3Reader来解析XML文档
		//XPP3Reader reader = new XPP3Reader();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("book.xml"));
		//使用访问者模式来访问XML文档。
		doc.accept(new YeekuVistor());
	}
}
//定义一个Visitor实现类。
class YeekuVistor extends VisitorSupport
{
	//保存当前正在处理的节点
	private String currentElement;
	//当Visitor访问元素时回调该方法
	public void visit(Element node)
	{
		//如果节点内容全部是文本
		if (node.isTextOnly())
		{
			System.out.println(node.getName() + "元素的值是："
				+ node.getText());
		}
		currentElement = node.getName();
	}
	//当Visitor访问属性时回调该方法
	public void visit(Attribute node)
	{
		System.out.println(currentElement + "元素的"
			+ node.getName() + "属性的值是："
			+ node.getText());
	}
	//当Visitor访问处理指令时回调该方法
	public void visit(ProcessingInstruction node)
	{
		System.out.println("处理指令"
			+ node.getTarget() + "的内容是："
			+ node.getText());
	}
}