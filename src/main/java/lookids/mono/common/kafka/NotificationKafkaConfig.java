package lookids.mono.common.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
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
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;

import lombok.extern.log4j.Log4j2;
import lookids.mono.notification.dto.in.NotificationChattingRequestDto;
import lookids.mono.notification.dto.in.NotificationCommentReplyRequestDto;
import lookids.mono.notification.dto.in.NotificationCommentRequestDto;
import lookids.mono.notification.dto.in.NotificationFavoriteRequestDto;
import lookids.mono.notification.dto.in.NotificationFeedRequestDto;
import lookids.mono.notification.dto.in.NotificationFollowRequestDto;

@EnableKafka
@Configuration
@Log4j2
public class NotificationKafkaConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	// @Value("${spring.kafka.cluster.uri}")
	// private String bootstrapServers;

	@Value("${consumer-group-id.feed}")
	private String feedGroupId;
	@Value("${consumer-group-id.chatting}")
	private String chattingGroupId;
	@Value("${consumer-group-id.favorite}")
	private String favoriteGroupId;
	@Value("${consumer-group-id.follow}")
	private String followGroupId;
	@Value("${consumer-group-id.comment}")
	private String commentGroupId;
	@Value("${consumer-group-id.comment-reply}")
	private String commentReplyGroupId;

	@Bean
	public DeadLetterPublishingRecoverer deadLetterPublishingRecoverer(
		KafkaTemplate<String, NotificationFeedRequestDto> kafkaTemplate) {
		return new DeadLetterPublishingRecoverer(kafkaTemplate,
			(record, ex) -> new TopicPartition(record.topic() + "-dead-letter", record.partition()));
	}

	@Bean
	public DefaultErrorHandler errorHandler(DeadLetterPublishingRecoverer recoverer) {
		return new DefaultErrorHandler(recoverer, new FixedBackOff(2000L, 5));
	}

	@Bean
	public Map<String, Object> notiFeedProducerConfigs() {
		Map<String, Object> producerProps = new HashMap<>();
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return producerProps;
	}

	@Bean
	public KafkaTemplate<String, NotificationFeedRequestDto> kafkaFeedTemplate() {
		ProducerFactory<String, NotificationFeedRequestDto> feedProducerFactory = new DefaultKafkaProducerFactory<>(
			notiFeedProducerConfigs());
		return new KafkaTemplate<>(feedProducerFactory);
	}

	@Bean
	public ConsumerFactory<String, NotificationFeedRequestDto> notificationFeedConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		// Kafka 브로커 주소 설정
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		// Consumer 그룹 ID 설정
		// 같은 그룹의 consumer들은 토픽의 파티션을 분배하여 메시지를 소비
		props.put(ConsumerConfig.GROUP_ID_CONFIG, feedGroupId);
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
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(NotificationFeedRequestDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, NotificationFeedRequestDto> notificationFeedEventListenerContainerFactory() {
		// @KafkaListener 어노테이션이 사용할 Factory 설정
		ConcurrentKafkaListenerContainerFactory<String, NotificationFeedRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(notificationFeedConsumerFactory());
		// DeadLetterPublishingRecoverer 연결
		factory.setCommonErrorHandler(errorHandler(deadLetterPublishingRecoverer(kafkaFeedTemplate())));
		return factory;
	}

	@Bean
	public ConsumerFactory<String, NotificationChattingRequestDto> notificationChattingConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		// Kafka 브로커 주소 설정
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		// Consumer 그룹 ID 설정
		// 같은 그룹의 consumer들은 토픽의 파티션을 분배하여 메시지를 소비
		props.put(ConsumerConfig.GROUP_ID_CONFIG, chattingGroupId);
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
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(NotificationChattingRequestDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, NotificationChattingRequestDto> notificationChattingEventListenerContainerFactory() {
		// @KafkaListener 어노테이션이 사용할 Factory 설정
		ConcurrentKafkaListenerContainerFactory<String, NotificationChattingRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(notificationChattingConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, NotificationFavoriteRequestDto> notificationFavoriteConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		// Kafka 브로커 주소 설정
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		// Consumer 그룹 ID 설정
		// 같은 그룹의 consumer들은 토픽의 파티션을 분배하여 메시지를 소비
		props.put(ConsumerConfig.GROUP_ID_CONFIG, favoriteGroupId);
		// 메시지 키의 역직렬화 설정 (String 타입)
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		// 메시지 값의 역직렬화 설정 (JSON -> KafkaFeedRequestDto)
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		// JSON을 자바 객체로 변환할 때 신뢰할 패키지 설정 ("*"는 모든 패키지 허용)
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(NotificationFavoriteRequestDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, NotificationFavoriteRequestDto> notificationFavoriteEventListenerContainerFactory() {
		// @KafkaListener 어노테이션이 사용할 Factory 설정
		ConcurrentKafkaListenerContainerFactory<String, NotificationFavoriteRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(notificationFavoriteConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, NotificationFollowRequestDto> notificationFollowConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		// Kafka 브로커 주소 설정
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		// Consumer 그룹 ID 설정
		// 같은 그룹의 consumer들은 토픽의 파티션을 분배하여 메시지를 소비
		props.put(ConsumerConfig.GROUP_ID_CONFIG, followGroupId);
		// 메시지 키의 역직렬화 설정 (String 타입)
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		// 메시지 값의 역직렬화 설정 (JSON -> KafkaFeedRequestDto)
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		// JSON을 자바 객체로 변환할 때 신뢰할 패키지 설정 ("*"는 모든 패키지 허용)
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(NotificationFollowRequestDto.class, false)));

	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, NotificationFollowRequestDto> notificationFollowEventListenerContainerFactory() {
		// @KafkaListener 어노테이션이 사용할 Factory 설정
		ConcurrentKafkaListenerContainerFactory<String, NotificationFollowRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(notificationFollowConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, NotificationCommentRequestDto> notificationCommentConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		// Kafka 브로커 주소 설정
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		// Consumer 그룹 ID 설정
		// 같은 그룹의 consumer들은 토픽의 파티션을 분배하여 메시지를 소비
		props.put(ConsumerConfig.GROUP_ID_CONFIG, commentGroupId);
		// 메시지 키의 역직렬화 설정 (String 타입)
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		// 메시지 값의 역직렬화 설정 (JSON -> KafkaFeedRequestDto)
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		// JSON을 자바 객체로 변환할 때 신뢰할 패키지 설정 ("*"는 모든 패키지 허용)
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(NotificationCommentRequestDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, NotificationCommentRequestDto> notificationCommentEventListenerContainerFactory() {
		// @KafkaListener 어노테이션이 사용할 Factory 설정
		ConcurrentKafkaListenerContainerFactory<String, NotificationCommentRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(notificationCommentConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, NotificationCommentReplyRequestDto> notificationCommentReplyConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		// Kafka 브로커 주소 설정
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		// Consumer 그룹 ID 설정
		// 같은 그룹의 consumer들은 토픽의 파티션을 분배하여 메시지를 소비
		props.put(ConsumerConfig.GROUP_ID_CONFIG, commentReplyGroupId);
		// 메시지 키의 역직렬화 설정 (String 타입)
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		// 메시지 값의 역직렬화 설정 (JSON -> KafkaFeedRequestDto)
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		// JSON을 자바 객체로 변환할 때 신뢰할 패키지 설정 ("*"는 모든 패키지 허용)
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(NotificationCommentReplyRequestDto.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, NotificationCommentReplyRequestDto> notificationCommentReplyEventListenerContainerFactory() {
		// @KafkaListener 어노테이션이 사용할 Factory 설정
		ConcurrentKafkaListenerContainerFactory<String, NotificationCommentReplyRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(notificationCommentReplyConsumerFactory());
		return factory;
	}

}
