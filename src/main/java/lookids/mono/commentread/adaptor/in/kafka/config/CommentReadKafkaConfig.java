package lookids.mono.commentread.adaptor.in.kafka.config;

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

import lookids.mono.comment.vo.out.CommentKafkaVo;
import lookids.mono.comment.vo.out.ReplyKafkaVo;
import lookids.mono.commentread.adaptor.in.kafka.event.CommentEvent;
import lookids.mono.commentread.adaptor.in.kafka.event.NicknameEvent;
import lookids.mono.commentread.adaptor.in.kafka.event.ProfileImageEvent;
import lookids.mono.commentread.adaptor.in.kafka.event.ReplyEvent;
import lookids.mono.commentread.adaptor.in.kafka.event.UserProfileEvent;

@EnableKafka
@Configuration
public class CommentReadKafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	private Map<String, Object> commonConsumerProps(String groupId) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000");
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "500");
		props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "300000");
		return props;
	}

	private <T> ConsumerFactory<String, T> createConsumerFactory(Class<T> targetType, String groupId) {
		return new DefaultKafkaConsumerFactory<>(commonConsumerProps(groupId), new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(targetType, false)));
	}

	private <T> ConcurrentKafkaListenerContainerFactory<String, T> createListenerContainerFactory(
		ConsumerFactory<String, T> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConsumerFactory<String, CommentEvent> commentReadConsumerFactory() {
		return createConsumerFactory(CommentEvent.class, "comment-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CommentEvent> commentReadListenerContainerFactory() {
		return createListenerContainerFactory(commentReadConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, ReplyEvent> replyReadConsumerFactory() {
		return createConsumerFactory(ReplyEvent.class, "comment-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ReplyEvent> replyReadListenerContainerFactory() {
		return createListenerContainerFactory(replyReadConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, UserProfileEvent> userProfileCommentConsumerFactory() {
		return createConsumerFactory(UserProfileEvent.class, "comment-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserProfileEvent> userProfileCommentListenerContainerFactory() {
		return createListenerContainerFactory(userProfileCommentConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, NicknameEvent> nicknameCommentConsumerFactory() {
		return createConsumerFactory(NicknameEvent.class, "comment-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, NicknameEvent> nicknameCommentListenerContainerFactory() {
		return createListenerContainerFactory(nicknameCommentConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, ProfileImageEvent> profileImageCommentConsumerFactory() {
		return createConsumerFactory(ProfileImageEvent.class, "comment-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ProfileImageEvent> profileImageCommentListenerContainerFactory() {
		return createListenerContainerFactory(profileImageCommentConsumerFactory());
	}

	@Bean
	public Map<String, Object> commentProducerConfigs() {
		Map<String, Object> producerProps = new HashMap<>();
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return producerProps;
	}

	@Bean
	public ProducerFactory<String, CommentKafkaVo> createCommentEvent() {
		return new DefaultKafkaProducerFactory<>(commentProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, CommentKafkaVo> commentkafkaTemplate() {
		return new KafkaTemplate<>(createCommentEvent());
	}

	@Bean
	public ProducerFactory<String, ReplyKafkaVo> createReplyEvent() {
		return new DefaultKafkaProducerFactory<>(commentProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, ReplyKafkaVo> replykafkaTemplate() {
		return new KafkaTemplate<>(createReplyEvent());
	}
}
