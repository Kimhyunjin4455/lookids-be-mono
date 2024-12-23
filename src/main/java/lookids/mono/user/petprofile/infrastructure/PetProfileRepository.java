package lookids.user.petprofile.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lookids.user.petprofile.domain.PetProfile;

public interface PetProfileRepository extends JpaRepository<PetProfile, Long> {

	Optional<PetProfile> findByPetCode(String uuid);

	List<PetProfile> findByUserUuid(String uuid);

	Optional<PetProfile> findByUserUuidAndPetCode(String userUuid, String petCode);

	Optional<PetProfile> findById(Long id);

	@Query(value = "SELECT * FROM pet_profile ORDER BY RAND() LIMIT :limit", nativeQuery = true)
	List<PetProfile> findRandomEntities(@Param("limit") int limit);

	@Query(value = "SELECT * FROM pet_profile WHERE user_uuid != :excludedUuid ORDER BY RAND() LIMIT :limit", nativeQuery = true)
	List<PetProfile> findRandomEntitiesExcludingUuid(@Param("excludedUuid") String excludedUuid,
		@Param("limit") int limit);
}
