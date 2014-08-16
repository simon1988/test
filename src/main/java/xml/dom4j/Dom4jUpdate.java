package xml.dom4j;

import java.io.*;
import org.dom4j.*;
import org.dom4j.io.*;

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
public class Dom4jUpdate
{
	public static void main(String[] args) throws Exception
	{
		//使用SAXReader来解析XML文档
		SAXReader  reader = new SAXReader();
		//忽略元素的开始标签、结束标签之间的空白
		reader.setStripWhitespaceText(true);
		//将元素之间相邻的文本内容合并处理
		reader.setMergeAdjacentText(true);
		Document doc = reader.read(new File("book.xml"));
		//获取XML文档的根元素
		Element root = doc.getRootElement();
		//创建一个“计算机书籍”子元素
		Element pcBook = root.addElement("计算机书籍");
		//添加一个随机数作为isbn属性值
		pcBook.addAttribute("isbn" , Math.round((Math.random() * 1000)) + ""); 
		//为“计算机书籍”元素添加“书名”子元素，并设置属性值
		Element name = pcBook.addElement("书名");
		name.setText("疯狂Java讲义");
		//为“计算机书籍”元素添加“价格”子元素，并设置属性值
		Element price = pcBook.addElement("价格");
		price.setText(99.00 + "");
		//定义一个输出格式对象
		OutputFormat format = new OutputFormat("	", true, "GBK"); 
		FileWriter fw = new FileWriter("book.xml");
		//定义一份XMLWriter对象
		XMLWriter writer = new XMLWriter(fw , format); 
		writer.write(doc);  
		fw.close();
	}
}
