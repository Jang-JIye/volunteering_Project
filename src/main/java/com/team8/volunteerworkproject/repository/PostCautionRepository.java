package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.PostCaution;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCautionRepository extends JpaRepository<PostCaution, Long> {
  Optional<PostCaution> findbyVolunteerCaution(long postCautionId);

}
