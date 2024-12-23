package lookids.auth.auth.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lookids.auth.auth.dto.in.PostUserRequestDto;
import lookids.auth.common.entity.BaseResponse;

@FeignClient(name = "user-service", url = "https://api.lookids.online:8000/user-service")
public interface UserServiceClient {
	@PostMapping("/auth/userprofile")
	BaseResponse<Void> writeUserProfile(@RequestBody PostUserRequestDto requestDto);
}
