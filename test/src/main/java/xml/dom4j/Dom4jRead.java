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
public class Dom4jRead
{
	public static void main(String[] args) throws Exception
	{
		//使用XPP3Reader来解析XML文档
//		XPP3Reader reader = new XPP3Reader();
//		//使用SAXReader来解析XML文档
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("book.xml"));
		Element root = doc.getRootElement();
		parse(root);
	}
	public static void parse(Element ele)
	{
		//处理当前元素包含的所有属性
		parseAttribute(ele);
		//获取当前元素包含的所有子元素
		List el = ele.elements(); 
		//遍历每个子元素
		for (Object e : el)
		{
			Element element = (Element)e;
			//如果该元素的内容不是只包含字符串
			if (!element.isTextOnly())
			{
				parse(element);
			}
			else
			{
				//处理当前元素的全部属性
				parseAttribute(element);
				//获取当前元素的内容
				System.out.println(element.getQName().getName() 
					+ "--->" + element.getText());
			}
		}
	}
	//定义一个方法处理指定元素的所有属性
	public static void parseAttribute(Element ele)
	{
		//获取Element元素的所有属性
		List attList = ele.attributes();
		//遍历Element元素每个属性 
		for (Object e : attList)
		{
			Attribute attr = (Attribute)e;
			//访问当前元素的每个属性的属性值
			System.out.println(ele.getQName().getName() + "元素的"
				+ attr.getQName().getName() + "属性值为：" 
				+ attr.getValue());
		}
	}
}
