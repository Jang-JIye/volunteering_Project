package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {
  Optional<Profile> findByUserId(String userId);

}
