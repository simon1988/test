package net.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSListener implements MessageListener{

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

		consumer.setMessageListener(new JMSListener());
		
		connection.start();

//		consumer.close();
//		session.close();
//		connection.close();
		
	}

	@Override
	public void onMessage(Message message) {
		
		TextMessage textMessage = (TextMessage) message;

		String text = "";
		try {
			text = textMessage.getText();
		} catch (JMSException e) {
			e.printStackTrace();
		}

		System.out.println("从ActiveMQ取回一条消息: " + text);
	}

}
