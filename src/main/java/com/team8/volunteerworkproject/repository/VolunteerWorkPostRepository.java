package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VolunteerWorkPostRepository extends JpaRepository<VolunteerWorkPost, Long> {
    Optional<VolunteerWorkPost> findByPostId(Long postId);
    Optional<VolunteerWorkPost> findByPostIdAndUserId(Long postId, String userId);
    List<VolunteerWorkPost> findAllByOrderByCreatedAtDesc();
}
