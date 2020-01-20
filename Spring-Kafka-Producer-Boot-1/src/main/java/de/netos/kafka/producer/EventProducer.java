package de.netos.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import de.netos.kafka.domain.MessageObject;

@Component
public class EventProducer {

	@Autowired
	@Qualifier("string")
	private KafkaTemplate<String, String> kafkaTemplateString;
	
	@Autowired
	@Qualifier("json")
	private KafkaTemplate<String, MessageObject> kafkaTemplateObject;
	
	public void sendMessage(String topic, String key, String data) {
		kafkaTemplateString.send(topic, key, data);
	}
	
	public void sendMessageObject(String topic, String key, MessageObject data) {
		kafkaTemplateObject.send(topic, key, data);
	}
}
