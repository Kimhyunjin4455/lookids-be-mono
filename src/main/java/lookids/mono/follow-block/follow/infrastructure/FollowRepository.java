package Lookids.follow.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Lookids.follow.domain.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {

	List<Follow> findByFollowingUuid(String followingUuid);
	List<Follow> findByFollowerUuid(String followerUuid);
	Optional<Follow> findByFollowerUuidAndFollowingUuid(String followerUuid, String followingUuid);
	Boolean existsByFollowerUuidAndFollowingUuid(String followerUuid, String followingUuid);
	Boolean existsByFollowingUuid(String followingUuid);
}
