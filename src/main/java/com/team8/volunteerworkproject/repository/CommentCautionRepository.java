package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.CommentCaution;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentCautionRepository extends JpaRepository<CommentCaution, Long> {


  @Override
  Optional<CommentCaution> findById(Long cautionId);

  List<CommentCaution> findAllByOrderByModifiedAtDesc();


}
