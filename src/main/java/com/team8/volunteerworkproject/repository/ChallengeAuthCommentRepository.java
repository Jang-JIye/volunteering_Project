package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.ChallengeAuth;
import com.team8.volunteerworkproject.entity.ChallengeAuthComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ChallengeAuthCommentRepository extends JpaRepository<ChallengeAuthComment, Long> {

    List<ChallengeAuthComment> findAllByChallengeAuth(ChallengeAuth challengeAuth);
}
