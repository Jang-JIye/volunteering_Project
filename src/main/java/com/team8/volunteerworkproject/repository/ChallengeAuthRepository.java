package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.ChallengeAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeAuthRepository extends JpaRepository<ChallengeAuth, Long> {

    Optional<ChallengeAuth> findByChallengeAuthId(Long challengeAuthId);
    Optional<ChallengeAuth> findByChallengeAuthIdAndUserId(Long challengeAuthId, String userId);
    List<ChallengeAuth> findAllByUserId(String userId);

}
