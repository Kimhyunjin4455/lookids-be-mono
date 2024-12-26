package lookids.mono.common.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
public class FirebaseConfig {

	@Bean
	public FirebaseMessaging firebaseMessaging() throws IOException {
		FileInputStream notificationAccount = new FileInputStream(
			"src/main/resources/lookids-df03a-firebase-adminsdk-d6ju3-612a1ef009.json");

		// GoogleCredentials.fromStream()을 사용하여 Firebase의 인증을 처리, json 파일을 읽고, 이를 통해 Firebase 인증을 구성
		GoogleCredentials credentials = GoogleCredentials.fromStream(notificationAccount);

		FirebaseOptions options = FirebaseOptions.builder().setCredentials(credentials).build();
		FirebaseApp app = FirebaseApp.initializeApp(options);
		return FirebaseMessaging.getInstance(app);
	}
}
