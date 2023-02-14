package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.VolunteerWorkPostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VolunteerWorkPostLikeRepository extends JpaRepository<VolunteerWorkPostLike, Long> {

    Optional<VolunteerWorkPostLike> findByPostIdAndUserId(Long postId, String userId);
    Optional<VolunteerWorkPostLike> deleteByPostIdAndUserId(Long postId, String userId);
    List<VolunteerWorkPostLike> findAllByPostId(Long postId);
}
