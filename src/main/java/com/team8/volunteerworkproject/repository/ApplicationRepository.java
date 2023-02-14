package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.Application;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.UserServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository <UserRepository, String> {

    Optional<UserRepository>findByUserId();

