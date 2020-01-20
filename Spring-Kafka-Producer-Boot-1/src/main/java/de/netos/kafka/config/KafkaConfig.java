package de.netos.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import de.netos.kafka.domain.MessageObject;

@Configuration
public class KafkaConfig {

	@Value("${broker}")
	private String bootstrapServer;
	
	public ProducerFactory<String, String> stringProducerFactory() {
		Map<String,Object> config = new HashMap<String, Object>();
		
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		
		return new DefaultKafkaProducerFactory<String, String>(config);
	}
	
	public ProducerFactory<String, MessageObject> jsonProducerFactory() {
		Map<String,Object> config = new HashMap<String, Object>();
		
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		
		return new DefaultKafkaProducerFactory<String, MessageObject>(config, new StringSerializer(), new JsonSerializer<MessageObject>());
	}
	
	@Bean("string")
	public KafkaTemplate<String, String> stringKafkaTemplate() {
		return new KafkaTemplate<String, String>(stringProducerFactory());
	}
	
	@Bean("json")
	public KafkaTemplate<String, MessageObject> jsonKafkaTemplate() {
		return new KafkaTemplate<String, MessageObject>(jsonProducerFactory());
	}
}
