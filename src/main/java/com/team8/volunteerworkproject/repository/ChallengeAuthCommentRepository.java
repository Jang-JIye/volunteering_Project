package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.ChallengeAuthComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeAuthCommentRepository extends JpaRepository<ChallengeAuthComment, Long> {

    //댓글 전체 조회
    List<ChallengeAuthComment> findAllByOrderByModifiedAtDesc();

    // 댓글 선택 조회
    Optional<ChallengeAuthComment> findById(Long challengeAuthCommentId);
}
