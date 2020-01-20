package de.netos.kafka.consumer;

import java.util.Map;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import de.netos.kafka.domain.MessageObject;
import de.netos.kafka.streams.EventsStreams;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventsListener {

	@StreamListener(value = EventsStreams.INPUT)
	public void handleEvents(@Headers Map<String, Object> headers,
			@Payload String data) {
		String key = new String((byte[]) headers.get("kafka_receivedMessageKey"));
		log.info("Receiving. Key: {}, Data {}", key, data);
	}
	
	@StreamListener(value = EventsStreams.INPUT_JSON)
	public void handleEventsJson(@Headers Map<String, Object> headers,
			@Payload MessageObject data) {
		String key = new String((byte[]) headers.get("kafka_receivedMessageKey"));
		log.info("Receiving. Key: {}, Data {}", key, data);
	}
}
