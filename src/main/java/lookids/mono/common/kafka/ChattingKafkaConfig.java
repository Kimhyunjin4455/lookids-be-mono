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

import lookids.mono.chatting.dto.in.UserKafkaRequestDto;
import lookids.mono.chatting.dto.out.NotificationKafkaRequestDto;

@EnableKafka
@Configuration
public class ChattingKafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public KafkaTemplate<String, NotificationKafkaRequestDto> chattingKafkaTemplate(
		ProducerFactory<String, NotificationKafkaRequestDto> producerFactory) {
		return new KafkaTemplate<>(producerFactory);
	}

	@Bean
	public ProducerFactory<String, NotificationKafkaRequestDto> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // JSON 직렬화 설정
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	//유저 삭제 시 발생하는 이벤트
	@Bean
	public ConsumerFactory<String, UserKafkaRequestDto> chatConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		// Kafka 브로커 주소 설정
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		// Consumer 그룹 ID 설정
		// 같은 그룹의 consumer들은 토픽의 파티션을 분배하여 메시지를 소비
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "user-chat");
		// 메시지 키의 역직렬화 설정 (String 타입)
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		// 메시지 값의 역직렬화 설정 (JSON -> KafkaFeedRequestDto)
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		// JSON을 자바 객체로 변환할 때 신뢰할 패키지 설정 ("*"는 모든 패키지 허용)
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		// Consumer Factory 생성
		// StringDeserializer: 키를 String으로 역직렬화
		// ErrorHandlingDeserializer: 역직렬화 실패 시 에러 처리
		// JsonDeserializer: JSON을 KafkaFeedRequestDto로 변환
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(UserKafkaRequestDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserKafkaRequestDto> userEventListenerContainerFactory() {
		// @KafkaListener 어노테이션이 사용할 Factory 설정
		ConcurrentKafkaListenerContainerFactory<String, UserKafkaRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(chatConsumerFactory());
		return factory;
	}

}