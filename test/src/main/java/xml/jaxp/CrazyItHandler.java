package xml.jaxp;

import org.xml.sax.helpers.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
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
public class CrazyItHandler extends DefaultHandler
{
	//定义一个成员变量来保存当前正在处理的Tag
	private String currentTag;
	//每当处理文本数据将触发该方法
	public void characters(char[] ch, int start, int length) 
	{
		String content = new String(ch , start , length);
		if (content.trim().length() > 0)
		{
			System.out.println("<" + currentTag + ">元素的值是："
				+ content.trim());
		}
	}
	//处理文档结束时触发该方法。
	public void endDocument()
	{
		System.out.println("解析文档结束");
	}
	//处理元素结束时触发该方法。
	public void endElement(String uri , String localName
		, String qName) 
	{
		System.out.println("处理元素结束:" + qName);
	}
	//处理元素里命名空间属性结束时触发该方法
	public void endPrefixMapping(String prefix) 
	{
		System.out.println("<" + currentTag + ">元素的命名空间属性的前缀："
			+ prefix);
	}
	//处理元素内容中可忽略的空白时触发该方法。
	public void ignorableWhitespace(char[] ch, int start, int length) 
	{
	}
	//解析处理指令时触发该方法。
	public void processingInstruction(String target , String data) 
	{
		System.out.println("当前处理指令是：" + target);
		System.out.println("处理指令数据为：" + data);
	}
	//跳过实体时触发该方法
	public void skippedEntity(String name) 
	{
		System.out.println("SAX解析器跳过的实体名为：" + name);
	}
	//开始解析XML文档时触发该方法
	public void startDocument() 
	{
		System.out.println("开始解析文档");
	}
	//开始处理元素时触发该方法。
	public void startElement(String uri , String localName
		, String qName , Attributes atts) 
	{
		System.out.println("开始处理元素:" + qName);
		currentTag = qName;
		//attrs代表该元素包含的所有属性
		int len = atts.getLength();
		if (len > 0)
		{
			System.out.println("<" + currentTag + ">元素的属性如下:" );
			for (int i = 0; i < len ; i++ )
			{
				System.out.println(atts.getQName(i) + "--->"
					+ atts.getValue(i));
			}
		}
	}
	//开始处理元素里命名空间属性时触发该方法
	public void startPrefixMapping(String prefix, String uri)
	{
		System.out.println("<" + currentTag + ">元素的命名空间属性的前缀："
			+ prefix);
		System.out.println("<" + currentTag + ">元素的命名空间属性的URI为："
			+ uri);
	}
	//当遇到DTD中的符号定义时触发该方法
	public void notationDecl(String name , String publicId
		 , String systemId)throws SAXException
	{
		System.out.println("当前符号的名字是：" + name); 
		System.out.println("当前符号的PUBLIC URI：" + publicId); 
		System.out.println("当前符号的SYSTEM URI：" + systemId); 
	}
	//当遇到DTD中的未解析实体时触发该方法
	public void unparsedEntityDecl(String name , String publicId
		, String systemId , String notationName)throws SAXException
	{
		System.out.println("当前未解析实体的名字是：" + name); 
		System.out.println("当前未解析实体的PUBLIC URI：" + publicId); 
		System.out.println("当前未解析实体的SYSTEM URI：" + systemId);
		System.out.println("当前未解析实体关联的符号名：" + notationName);
	}
}
