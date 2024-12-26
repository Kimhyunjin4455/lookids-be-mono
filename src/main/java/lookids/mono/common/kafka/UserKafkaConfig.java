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

import lookids.mono.user.petprofile.vo.in.FeedKafkaVo;
import lookids.mono.user.petprofile.vo.out.PetProfileDeleteKafkaVo;
import lookids.mono.user.petprofile.vo.out.PetProfileKafkaVo;
import lookids.mono.user.petprofile.vo.out.PetProfileSearchKafkaVo;
import lookids.mono.user.userprofile.vo.in.CommentEventVo;
import lookids.mono.user.userprofile.vo.in.FeedEventVo;
import lookids.mono.user.userprofile.vo.in.FollowEventVo;
import lookids.mono.user.userprofile.vo.in.ReplyEventVo;
import lookids.mono.user.userprofile.vo.out.FollowKafkaVo;
import lookids.mono.user.userprofile.vo.out.NicknameKafkaVo;
import lookids.mono.user.userprofile.vo.out.ProfileImageKafkaVo;
import lookids.mono.user.userprofile.vo.out.UserProfileKafkaVo;

@EnableKafka
@Configuration
public class UserKafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public ConsumerFactory<String, CommentEventVo> commentUserConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "comment-join-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		//props.put(JsonDeserializer.TYPE_MAPPINGS, "lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(CommentEventVo.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CommentEventVo> commentUserListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, CommentEventVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(commentUserConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, ReplyEventVo> replyUserConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "comment-join-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		//props.put(JsonDeserializer.TYPE_MAPPINGS, "lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(ReplyEventVo.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ReplyEventVo> replyUserListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ReplyEventVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(replyUserConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, FeedEventVo> feedUserConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "comment-join-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		//props.put(JsonDeserializer.TYPE_MAPPINGS, "lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(FeedEventVo.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FeedEventVo> feedUserListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, FeedEventVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(feedUserConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, FeedKafkaVo> feedUserKafkaConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "comment-join-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		//props.put(JsonDeserializer.TYPE_MAPPINGS, "lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(FeedKafkaVo.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FeedKafkaVo> feedUserKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, FeedKafkaVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(feedUserKafkaConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, FollowEventVo> followUserKafkaConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "comment-join-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		//props.put(JsonDeserializer.TYPE_MAPPINGS, "lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(FollowEventVo.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FollowEventVo> followUserListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, FollowEventVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(followUserKafkaConsumerFactory());
		return factory;
	}

	@Bean
	public Map<String, Object> userProducerConfigs() {
		Map<String, Object> producerProps = new HashMap<>();
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return producerProps;
	}

	@Bean
	public ProducerFactory<String, ProfileImageKafkaVo> updateImageEvent() {
		return new DefaultKafkaProducerFactory<>(userProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, ProfileImageKafkaVo> imageKafkaTemplate() {
		return new KafkaTemplate<>(updateImageEvent());
	}

	@Bean
	public ProducerFactory<String, NicknameKafkaVo> updateNicknameEvent() {
		return new DefaultKafkaProducerFactory<>(userProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, NicknameKafkaVo> nicknameKafkaTemplate() {
		return new KafkaTemplate<>(updateNicknameEvent());
	}

	@Bean
	public ProducerFactory<String, FollowKafkaVo> joinFollowEvent() {
		return new DefaultKafkaProducerFactory<>(userProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, FollowKafkaVo> followJoinKafkaTemplate() {
		return new KafkaTemplate<>(joinFollowEvent());
	}

	@Bean
	public ProducerFactory<String, UserProfileKafkaVo> createUserEvent() {
		return new DefaultKafkaProducerFactory<>(userProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, UserProfileKafkaVo> userProfileKafkaTemplate() {
		return new KafkaTemplate<>(createUserEvent());
	}

	@Bean
	public ProducerFactory<String, PetProfileKafkaVo> createPetEvent() {
		return new DefaultKafkaProducerFactory<>(userProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, PetProfileKafkaVo> petProfileKafkaTemplate() {
		return new KafkaTemplate<>(createPetEvent());
	}

	@Bean
	public ProducerFactory<String, PetProfileSearchKafkaVo> searchPetEvent() {
		return new DefaultKafkaProducerFactory<>(userProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, PetProfileSearchKafkaVo> searchPetProfileKafkaTemplate() {
		return new KafkaTemplate<>(searchPetEvent());
	}

	@Bean
	public ProducerFactory<String, PetProfileDeleteKafkaVo> deletePetEvent() {
		return new DefaultKafkaProducerFactory<>(userProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, PetProfileDeleteKafkaVo> deletePetProfilekafkaTemplate() {
		return new KafkaTemplate<>(deletePetEvent());
	}
}
