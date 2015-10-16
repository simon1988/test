package net.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSServer {
	public static void main(String[] args) throws JMSException {

		String jmsProviderAddress = "tcp://localhost:61616";// 地址

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				jmsProviderAddress);// 连接器

		Connection connection = connectionFactory.createConnection();// 创建连接

		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);// 打开会话

		String destinationName = "demoQueue";

		Destination dest = session.createQueue(destinationName);// 消息目的地

		MessageConsumer consumer = session.createConsumer(dest);

		connection.start();

		Message message = consumer.receive();

		TextMessage textMessage = (TextMessage) message;

		String text = textMessage.getText();

		System.out.println("从ActiveMQ取回一条消息: " + text);

		consumer.close();
		session.close();
		connection.close();

	}

}
