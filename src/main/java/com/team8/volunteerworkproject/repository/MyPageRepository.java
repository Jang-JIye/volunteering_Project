package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageRepository extends JpaRepository<Profile, String> {
  Optional<Profile> findByUserId(String userId);

}
