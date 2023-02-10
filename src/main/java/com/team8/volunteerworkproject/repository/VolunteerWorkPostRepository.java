package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VolunteerWorkPostRepository extends JpaRepository<VolunteerWorkPost, Long> {
    List<VolunteerWorkPost> findAllByCreatedAtDesc();
}
