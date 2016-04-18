package net.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSListener implements MessageListener {
	
	private Session session;

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			String text = textMessage.getText();
			if(text!=null){
				System.out.println("从ActiveMQ取回一条消息: " + text);
				session.commit();
			}else{
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startConsuming() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Destination dest = session.createQueue("demoQueue");
		MessageConsumer consumer = session.createConsumer(dest);
		consumer.setMessageListener(this);
		connection.start();
	}

	public static void main(String[] args) throws Exception {
		JMSListener listener = new JMSListener();
		listener.startConsuming();
	}
}
