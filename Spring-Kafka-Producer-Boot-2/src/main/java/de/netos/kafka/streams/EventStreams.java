package de.netos.kafka.streams;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventStreams {

	public String OUTPUT_STRING = "events-string-out";
	public String OUTPUT_JSON = "events-json-out";
	
	@Output(OUTPUT_STRING)
	MessageChannel outboundStringEvents();
	
	@Output(OUTPUT_JSON)
	MessageChannel outboundJsonEvents();
}
