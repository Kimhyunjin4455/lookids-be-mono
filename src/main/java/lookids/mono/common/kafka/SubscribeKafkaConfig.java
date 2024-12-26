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
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import lookids.mono.subscribe.dto.in.FeedKafkaRequestDto;
import lookids.mono.subscribe.dto.in.NotificationKafkaRequestDto;

@EnableKafka
@Configuration
public class SubscribeKafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public ConsumerFactory<String, FeedKafkaRequestDto> subscribeFeedConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		// Kafka 브로커 주소 설정
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		// Consumer 그룹 ID 설정
		// 같은 그룹의 consumer들은 토픽의 파티션을 분배하여 메시지를 소비
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "feed-join-subscribe");
		// 메시지 키의 역직렬화 설정 (String 타입)
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		// 메시지 값의 역직렬화 설정 (JSON -> KafkaFeedRequestDto)
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		// JSON을 자바 객체로 변환할 때 신뢰할 패키지 설정 ("*"는 모든 패키지 허용)
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		//props.put(JsonDeserializer.TYPE_MAPPINGS, "lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo");

		// Consumer Factory 생성
		// StringDeserializer: 키를 String으로 역직렬화
		// ErrorHandlingDeserializer: 역직렬화 실패 시 에러 처리
		// JsonDeserializer: JSON을 KafkaFeedRequestDto로 변환
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(FeedKafkaRequestDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FeedKafkaRequestDto> subscribeFeedEventListenerContainerFactory() {
		// @KafkaListener 어노테이션이 사용할 Factory 설정
		ConcurrentKafkaListenerContainerFactory<String, FeedKafkaRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(subscribeFeedConsumerFactory());
		return factory;
	}

	@Bean
	public Map<String, Object> subscribeProducerConfigs() {
		Map<String, Object> producerProps = new HashMap<>();
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return producerProps;
	}

	@Bean
	public ProducerFactory<String, NotificationKafkaRequestDto> createFeedNotificationEvent() {
		return new DefaultKafkaProducerFactory<>(subscribeProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, NotificationKafkaRequestDto> subscribekafkaTemplate() {
		return new KafkaTemplate<>(createFeedNotificationEvent());
	}

}
