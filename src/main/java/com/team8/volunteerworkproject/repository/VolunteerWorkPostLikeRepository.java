package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.VolunteerWorkPostLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerWorkPostLikeRepository extends JpaRepository<VolunteerWorkPostLike, Long> {
  Optional<VolunteerWorkPostLike> findByVolunteerWorkPostId(long volunteerWorkPostLikeId);

}
