package socket;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaClient {
	private static final String TOPIC = "gjs_trade";
	private static final KafkaClient instance = new KafkaClient();
	private final Producer<String, String> producer;

	private KafkaClient() {
		Properties props = new Properties();
		props.put("metadata.broker.list", "223.252.197.84:9093,223.252.197.84:9094,223.252.197.84:9095");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("producer.type", "async");
		props.put("request.required.acks", "0");
		props.put("queue.enqueue.timeout.ms", "0");

		producer = new Producer<String, String>(new ProducerConfig(props));
	}
	public static KafkaClient getInstance(){
		return instance;
	}

	public void sendMessage(String msg) {
		producer.send(new KeyedMessage<String, String>(TOPIC, msg));
		producer.close();
	}

	public static void main(String[] args) {
		String msg = "{\"bankCardNo\":\"666614709632580745\",\"createTime\":1440487606000,\"currencyType\":\"00\",\"firmId\":\"16380494\",\"localOrderId\":\"150817001016\",\"notifyStatus\":0,\"orderId\":\"2015081716JY06306434\",\"orderMoney\":\"632.00\",\"orderMoneyCent\":63200,\"orderType\":\"deposit\",\"orderTypeDisplay\":\"1\",\"partnerId\":\"njs\",\"realTradeMoney\":\"632.00\",\"realTradeMoneyCent\":63200,\"realTradeTime\":1439799998000,\"reconStatus\":0,\"status\":2,\"updateTime\":1439823600000,\"userName\":\"fsx379@163.com\"}";
		getInstance().sendMessage(msg);
	}
}
