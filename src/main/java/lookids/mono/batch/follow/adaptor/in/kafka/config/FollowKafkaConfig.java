package lookids.mono.batch.follow.adaptor.in.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import lookids.mono.batch.follow.adaptor.in.kafka.event.FollowEvent;

@EnableKafka
@Configuration
public class FollowKafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${group-id.batch}")
	private String groupId;

	private Map<String, Object> commonConsumerProps(String groupId) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000");
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500); //한번의 poll에 반환할 최대 레코드수
		props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 60000); //컨슈머가 브로커에 응답하는 최대시간
		props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 3000); //브로커가 요청에 응답할 때 대기시간
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); //자동 커밋 비활성화
		return props;
	}

	private <T> ConsumerFactory<String, T> createConsumerFactory(Class<T> targetType, String groupId) {
		return new DefaultKafkaConsumerFactory<>(commonConsumerProps(groupId), new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(targetType, false)));
	}

	private <T> ConcurrentKafkaListenerContainerFactory<String, T> createBatchListenerContainerFactory(
		ConsumerFactory<String, T> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		factory.getContainerProperties().setIdleBetweenPolls(60000);
		factory.setBatchListener(true);  // 배치 리스너 설정
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL); // 수동 커밋 활성화
		return factory;
	}

	@Bean
	public ConsumerFactory<String, FollowEvent> followConsumerFactory() {
		return createConsumerFactory(FollowEvent.class, groupId);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FollowEvent> followEventListenerContainerFactory() {
		return createBatchListenerContainerFactory(followConsumerFactory());
	}

}
