package lookids.mono.common.utils;

import java.util.UUID;

public class UuidGenerator {
	private static final String NORMAL_PREFIX = "normal-";
	private static final String SOCIAL_PREFIX = "social-";

	public static String generateUuid(String prefix) {
		return prefix + UUID.randomUUID().toString();
	}

	public static String generateNormalUuid() {
		return generateUuid(NORMAL_PREFIX);
	}

	public static String generateSocialUuid() {
		return generateUuid(SOCIAL_PREFIX);
	}
}
