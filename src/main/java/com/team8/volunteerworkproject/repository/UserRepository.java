package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByUserId(String userId);

}
