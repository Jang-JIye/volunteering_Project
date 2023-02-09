package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.CommentCaution;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentCautionRepository extends JpaRepository<CommentCaution, Long> {
  Optional<CommentCaution> findByCommentCautionId(long commentCautionId);

}
