package de.netos.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import de.netos.kafka.streams.EventStreams;

@EnableBinding(EventStreams.class)
public class KafkaConfig {

}
