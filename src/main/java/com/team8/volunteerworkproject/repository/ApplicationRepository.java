package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository <Application, String> {

    Optional<Application>findByPostIdAndUserId(Long postId, String userId);

