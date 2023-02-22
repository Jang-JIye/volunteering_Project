package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.ChallengeAuthLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeAuthLikeRepository extends JpaRepository<ChallengeAuthLike, Long> {

    Optional<ChallengeAuthLike> findByChallengeAuthIdAndUserId(Long challengeAuthId, String userId);
    Optional<ChallengeAuthLike> deleteByChallengeAuthIdAndUserId(Long challengeAuthId, String userId);
    List<ChallengeAuthLike> findAllByChallengeAuthId(Long challengeAuthId);
}
