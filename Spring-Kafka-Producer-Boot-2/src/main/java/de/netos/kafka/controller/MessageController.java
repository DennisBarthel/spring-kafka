package de.netos.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.netos.kafka.producer.MessageProducer;

@RestController
public class MessageController {
	
	@Autowired
	private MessageProducer messageProducer;

	@GetMapping("/message/{text}")
	public void sendStringMessage(@PathVariable("text") String message) {
		messageProducer.sendStringMessage(message);
	}
	
	@GetMapping("/messageObject/{text}")
	public void sendJsonMessage(@PathVariable("text") String message) {
		messageProducer.sendStringJsonMessage(message);
	}
}
