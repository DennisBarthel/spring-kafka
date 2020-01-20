package de.netos.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import de.netos.kafka.domain.MessageObject;
import de.netos.kafka.streams.EventStreams;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageProducer {

	@Autowired
	private EventStreams eventStreams;
	
	public void sendStringMessage(String message) {
		String key = generateKey(message);
		log.info("Send Message. Key: {}, Data: {}", key, message);
		
		MessageChannel messageChannel = eventStreams.outboundStringEvents();
		messageChannel.send(MessageBuilder
				.withPayload(message)
				.setHeader(KafkaHeaders.MESSAGE_KEY, key)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN)
				.build());
	}
	
	public void sendStringJsonMessage(String message) {
		String key = generateKey(message);
		MessageObject data = new MessageObject(System.nanoTime(), message);
		
		log.info("Send Message. Key: {}, Data: {}", key, data);
		
		MessageChannel messageChannel = eventStreams.outboundJsonEvents();
		messageChannel.send(MessageBuilder
				.withPayload(data)
				.setHeader(KafkaHeaders.MESSAGE_KEY, key)
				.build());
	}
	
	private String generateKey(String message) {
		return String.valueOf(System.identityHashCode(message));
	}
}
