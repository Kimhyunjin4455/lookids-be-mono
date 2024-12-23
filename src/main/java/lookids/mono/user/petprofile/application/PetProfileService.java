package lookids.mono.user.petprofile.application;

import java.util.List;

import lookids.mono.user.petprofile.dto.in.PetProfileImgDto;
import lookids.mono.user.petprofile.dto.in.PetProfileRequestDto;
import lookids.mono.user.petprofile.dto.in.PetProfileUpdateDto;
import lookids.mono.user.petprofile.dto.in.PetProfileWeightDto;
import lookids.mono.user.petprofile.dto.out.PetProfileResponseDto;

public interface PetProfileService {
	void createPetProfile(PetProfileRequestDto petProfileRequestDto);

	void updatePetProfile(PetProfileUpdateDto petProfileUpdateDto);

	void updatePetProfileImage(PetProfileImgDto petProfileImgDto);

	void updatePetProfileWeight(PetProfileWeightDto petProfileWeightDto);

	void deletePetProfile(String petUuid);

	PetProfileResponseDto readPetProfile(String petUuid);

	List<PetProfileResponseDto> readPetProfileList(String userUuid);

	List<PetProfileResponseDto> reedRandomPetProfile(String uuid, Integer limit);
}
