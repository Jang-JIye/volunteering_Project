package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.Comment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Override
  Optional<Comment> findById(Long comment_id);
}
