package lookids.mono.common.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import lombok.extern.slf4j.Slf4j;
import lookids.mono.feed.dto.in.DeleteKafkaDto;
import lookids.mono.feed.dto.in.FeedKafkaDto;
import lookids.mono.feedread.dto.in.TargetRequestKafkaDto;

@Slf4j
@EnableKafka
@Configuration
public class FeedKafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;

	@Bean
	public Map<String, Object> feedProducerConfigs() {
		Map<String, Object> producerProps = new HashMap<>();
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return producerProps;
	}

	@Bean
	public ProducerFactory<String, FeedKafkaDto> createFeedNotification() {
		return new DefaultKafkaProducerFactory<>(feedProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, FeedKafkaDto> feedkafkaTemplate() {
		return new KafkaTemplate<>(createFeedNotification());
	}

	@Bean
	public Map<String, Object> feedDeleteProducerConfigs() {
		Map<String, Object> producerProps = new HashMap<>();
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return producerProps;
	}

	@Bean
	public ProducerFactory<String, DeleteKafkaDto> DeleteFeedNotification() {
		return new DefaultKafkaProducerFactory<>(feedProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, DeleteKafkaDto> deletekafkaTemplate() {
		return new KafkaTemplate<>(DeleteFeedNotification());
	}

	@Bean
	public ProducerFactory<String, TargetRequestKafkaDto> recommendKafka() {
		return new DefaultKafkaProducerFactory<>(feedProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, TargetRequestKafkaDto> recommendKafkaTemplate() {
		return new KafkaTemplate<>(recommendKafka());
	}
}