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
public class Dom4jWrite
{
	public static void main(String[] args) throws Exception
	{
		//创建一个DocumentFactory对象
		DocumentFactory factory = new DocumentFactory();
		//创建一个Document对象
		Document doc = factory.createDocument();
		//向Document对象中添加一个处理指令节点
		doc.addProcessingInstruction("crazyit" 
			, "website=\"http://www.crazyit.org\"");
		//向doc中添加根节点元素
		Element root = doc.addElement("书籍列表");
		//采用循环方式添加4个子元素
		for (int i = 0; i < 4 ; i++ )
		{
			//创建一个“计算机书籍”子元素
			Element pcBook = root.addElement("计算机书籍");
			//添加一个随机数作为isbn属性值
			pcBook.addAttribute("isbn" , Math.round((Math.random() * 1000)) + ""); 
			//为“计算机书籍”元素添加“书名”子元素，并设置属性值
			Element name = pcBook.addElement("书名");
			name.setText("书籍" + i);
			//为“计算机书籍”元素添加“价格”子元素，并设置属性值
			Element price = pcBook.addElement("价格");
			price.setText(i * 10 + "");
		}
		//定义一个输出格式对象
		OutputFormat format = new OutputFormat("	", true, "GBK"); 
		FileWriter fw = new FileWriter("book.xml");
		//定义一份XMLWriter对象
		XMLWriter writer = new XMLWriter(fw , format); 
		writer.write(doc);  
		fw.close();
	}
}
