package socket;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaClient {
	private static final String TOPIC = "gjs_q";
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
		getInstance().sendMessage("dtt2");
	}
}
