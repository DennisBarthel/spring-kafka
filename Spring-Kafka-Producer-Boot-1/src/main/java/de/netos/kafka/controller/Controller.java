package de.netos.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.netos.kafka.domain.MessageObject;
import de.netos.kafka.producer.EventProducer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Controller {
	
	@Autowired
	private EventProducer eventProducer;

	@GetMapping("/message/{text}")
	public void sendMessage(@PathVariable("text") String text) {
		String key = String.valueOf(System.identityHashCode(text));
		
		log.info("Sending event. Key: {}, Data: {}", key, text);
		eventProducer.sendMessage("events", key, text);
	}
	
	@GetMapping("/messageObject/{text}")
	public void sendMessageObject(@PathVariable("text") String text) {
		String key = String.valueOf(System.identityHashCode(text));
		MessageObject object = new MessageObject(System.nanoTime(), text);
		
		log.info("Sending object. Key: {}, Data: {}", key, object);
		eventProducer.sendMessageObject("events-objects", key, object);
	}
}
