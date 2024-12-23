package lookids.user.petprofile.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.user.common.entity.BaseResponse;
import lookids.user.petprofile.application.PetProfileService;
import lookids.user.petprofile.dto.out.PetProfileResponseDto;
import lookids.user.petprofile.vo.out.PetProfileResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/read/petprofile")
public class PetProfileReadController {

	private final PetProfileService petProfileService;

	@Operation(summary = "readPetProfileList API", description = "readPetProfileList API 입니다.")
	@GetMapping("/all")
	public BaseResponse<List<PetProfileResponseVo>> readPetProfileList(@RequestParam(value = "uuid") String uuid) {
		List<PetProfileResponseDto> petProfileResponseDtoList = petProfileService.readPetProfileList(uuid);
		return new BaseResponse<>(petProfileResponseDtoList.stream().map(PetProfileResponseDto::toVo).toList());
	}

	@Operation(summary = "readRandomPetProfileList API", description = "readPetProfileList API 입니다.")
	@GetMapping("/random")
	public BaseResponse<List<PetProfileResponseVo>> readRandomPetProfileList(@RequestHeader(value = "uuid") String uuid,
		@RequestParam(value = "limit") Integer limit) {
		List<PetProfileResponseDto> petProfileResponseDtoList = petProfileService.reedRandomPetProfile(uuid, limit);
		return new BaseResponse<>(petProfileResponseDtoList.stream().map(PetProfileResponseDto::toVo).toList());
	}

	@Operation(summary = "readPetProfile API", description = "readPetProfile API 입니다.")
	@GetMapping()
	public BaseResponse<PetProfileResponseVo> readPetProfile(@RequestParam(value = "petUuid") String petUuid) {
		return new BaseResponse<>(petProfileService.readPetProfile(petUuid).toVo());
	}
}
