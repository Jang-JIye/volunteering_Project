package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.Application;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
  Optional<Application> findByApplicationId(long applicationId);

}