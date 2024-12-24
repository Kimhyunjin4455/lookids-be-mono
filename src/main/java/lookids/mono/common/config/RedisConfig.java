package lookids.mono.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	// @Configuration은 클래스 수준에서 Spring 설정을 나타냄
	// @Bean은 메서드 수준에서 그 메서드가 반환하는 객체를 빈으로 등록
	// 설정된 빈은 다른 컴포넌트에서 의존성 주입을 통해 사용

	// RedisTemplate 빈 생성
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);

		// 키는 String으로 설정
		template.setKeySerializer(new StringRedisSerializer());

		template.setValueSerializer(new StringRedisSerializer()); // Redis의 값을 String 객체로 직렬화

		// 해시 키와 값도 설정
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new StringRedisSerializer()); // 해시의 값을 String 객체로 직렬화
		return template;
	}

}
