package lookids.mono.user.petprofile.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.user.petprofile.application.PetProfileService;
import lookids.mono.user.petprofile.dto.in.PetProfileImgDto;
import lookids.mono.user.petprofile.dto.in.PetProfileRequestDto;
import lookids.mono.user.petprofile.dto.in.PetProfileUpdateDto;
import lookids.mono.user.petprofile.dto.in.PetProfileWeightDto;
import lookids.mono.user.petprofile.vo.in.PetProfileImgVo;
import lookids.mono.user.petprofile.vo.in.PetProfileRequestVo;
import lookids.mono.user.petprofile.vo.in.PetProfileUpdateVo;
import lookids.mono.user.petprofile.vo.in.PetProfileWeightVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user-service/write/petprofile")
public class PetProfileWriteController {

	private final PetProfileService petProfileService;

	@Operation(summary = "createPetProfile API", description = "createPetProfile API 입니다.")
	@PostMapping()
	public BaseResponse<Void> createPetProfile(@RequestHeader("uuid") String uuid,
		@RequestBody PetProfileRequestVo petProfileRequestVo) {
		petProfileService.createPetProfile(PetProfileRequestDto.toDto(petProfileRequestVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updatePetProfile API", description = "updatePetProfile API 입니다.")
	@PutMapping()
	public BaseResponse<Void> updatePetProfile(@RequestBody PetProfileUpdateVo petProfileUpdateVo) {
		petProfileService.updatePetProfile(PetProfileUpdateDto.toDto(petProfileUpdateVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updatePetProfileImg API", description = "updatePetProfileImg API 입니다.")
	@PutMapping("/img")
	public BaseResponse<Void> updatePetProfileImg(@RequestHeader("uuid") String uuid,
		@RequestBody PetProfileImgVo petProfileImgVo) {
		petProfileService.updatePetProfileImage(PetProfileImgDto.toDto(petProfileImgVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updatePetProfileWeight API", description = "updatePetProfileWeight API 입니다.")
	@PutMapping("/weight")
	public BaseResponse<Void> updatePetProfileWeight(@RequestHeader("uuid") String uuid,
		@RequestBody PetProfileWeightVo petProfileWeightVo) {
		petProfileService.updatePetProfileWeight(PetProfileWeightDto.toDto(petProfileWeightVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "deletePetProfile API", description = "deletePetProfile API 입니다.")
	@DeleteMapping()
	public BaseResponse<Void> deletePetProfile(@RequestParam(value = "petUuid") String petUuid) {
		petProfileService.deletePetProfile(petUuid);
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
