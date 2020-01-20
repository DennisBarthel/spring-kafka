package de.netos.kafka.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EventsStreams {

	public String INPUT = "events-in";
	public String INPUT_JSON = "events-json-in";
	
	@Input(INPUT)
	SubscribableChannel inboundEvents();
	
	@Input(INPUT_JSON)
	SubscribableChannel inboundEventsJson();
}
