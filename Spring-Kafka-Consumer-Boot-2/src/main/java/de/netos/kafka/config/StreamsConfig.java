package de.netos.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import de.netos.kafka.streams.EventsStreams;

@EnableBinding(EventsStreams.class)
public class StreamsConfig {

}
