package lookids.mono.common.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import lookids.mono.map.dto.in.KafkaFeedDeleteRequestDto;
import lookids.mono.map.dto.out.FeedCodeResponseDto;

@Configuration
public class MapKafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;

	@Bean
	public ConsumerFactory<String, FeedCodeResponseDto> consumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "map-group");
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configProps.put(JsonDeserializer.TYPE_MAPPINGS,
			"feedCodeResponseDto:lookids.mono.map.dto.out.FeedCodeResponseDto");

		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(FeedCodeResponseDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FeedCodeResponseDto> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, FeedCodeResponseDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, KafkaFeedDeleteRequestDto> FeedDeleteConsumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "feeddelete-group");
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(KafkaFeedDeleteRequestDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, KafkaFeedDeleteRequestDto> FeedDeleteContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, KafkaFeedDeleteRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(FeedDeleteConsumerFactory());
		return factory;
	}

}
