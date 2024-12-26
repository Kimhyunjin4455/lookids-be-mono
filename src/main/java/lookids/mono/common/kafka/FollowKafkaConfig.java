package lookids.mono.common.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import lookids.mono.followblock.block.dto.in.KafkaBlockRequestDto;
import lookids.mono.followblock.block.dto.out.KafkaBlockResponseDto;
import lookids.mono.followblock.follow.dto.in.KafkaFollowDto;
import lookids.mono.followblock.follow.dto.in.KafkaFollowRequestDto;
import lookids.mono.followblock.follow.dto.in.KafkaUserUpdateRequestDto;
import lookids.mono.followblock.follow.dto.out.KafkaAlarmFollowResponseDto;
import lookids.mono.followblock.follow.dto.out.KafkaFollowResponseDto;
import lookids.mono.followblock.follow.dto.out.KafkaFollowerResponseDto;

@Configuration
public class FollowKafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;

	@Bean
	public Map<String, Object> followProducerConfigs() {
		Map<String, Object> producerProps = new HashMap<>();
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return producerProps;
	}

	@Bean
	public ProducerFactory<String, KafkaAlarmFollowResponseDto> alarmFollowKafkaResponseDtoProducerFactory() {
		return new DefaultKafkaProducerFactory<>(followProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, KafkaAlarmFollowResponseDto> alarmKafkaTemplate() {
		return new KafkaTemplate<>(alarmFollowKafkaResponseDtoProducerFactory());
	}

	@Bean
	public ProducerFactory<String, KafkaFollowResponseDto> feedFollowKafkaResponseDtoProducerFactory() {
		return new DefaultKafkaProducerFactory<>(followProducerConfigs());
	}

	@Bean
	KafkaTemplate<String, KafkaFollowResponseDto> feedKafkaTemplate() {
		return new KafkaTemplate<>(feedFollowKafkaResponseDtoProducerFactory());
	}

	@Bean
	public ProducerFactory<String, KafkaFollowerResponseDto> followerKafkaResponseDtoProducerFactory() {
		return new DefaultKafkaProducerFactory<>(followProducerConfigs());
	}

	@Bean
	KafkaTemplate<String, KafkaFollowerResponseDto> followerKafkaTemplate() {
		return new KafkaTemplate<>(followerKafkaResponseDtoProducerFactory());
	}

	@Bean
	public ProducerFactory<String, KafkaBlockResponseDto> blockKafkaResponseDtoProducerFactory() {
		return new DefaultKafkaProducerFactory<>(followProducerConfigs());
	}

	@Bean
	KafkaTemplate<String, KafkaBlockResponseDto> blockResKafkaTemplate() {
		return new KafkaTemplate<>(blockKafkaResponseDtoProducerFactory());
	}

	@Bean
	public ConsumerFactory<String, KafkaFollowRequestDto> FeedFollowconsumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "follow-group");
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(KafkaFollowRequestDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, KafkaFollowRequestDto> FeedFollowListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, KafkaFollowRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(FeedFollowconsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, KafkaFollowRequestDto> FollowerConsumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "follow-group");
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(KafkaFollowRequestDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, KafkaFollowRequestDto> FollowerListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, KafkaFollowRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(FollowerConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, KafkaBlockRequestDto> BlockConsumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "block-group");
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(KafkaBlockRequestDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, KafkaBlockRequestDto> BlockContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, KafkaBlockRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(BlockConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, KafkaFollowDto> FollowInfoConsumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "block-group");
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(KafkaFollowDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, KafkaFollowDto> FollowInfoContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, KafkaFollowDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(FollowInfoConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, KafkaUserUpdateRequestDto> UserUpdateConsumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "block-group");
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(KafkaUserUpdateRequestDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, KafkaUserUpdateRequestDto> UserUpdateContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, KafkaUserUpdateRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(UserUpdateConsumerFactory());
		return factory;
	}

}
