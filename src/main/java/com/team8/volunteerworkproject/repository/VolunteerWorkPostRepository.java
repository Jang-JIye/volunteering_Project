package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerWorkPostRepository extends JpaRepository<VolunteerWorkPost, Long> {

  @Override
  Optional<VolunteerWorkPost> findById(Long post_id);
}
