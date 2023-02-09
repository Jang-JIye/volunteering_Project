package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.Comment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  Optional<Comment> findByCommentId(long commentId);

}
