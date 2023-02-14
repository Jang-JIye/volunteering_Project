package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository <Application, String> {
    Optional<Application> findByPostIdAndUserId(Long postId, String userId);

    Optional<Application> delete(Long postId, String userId);
}

