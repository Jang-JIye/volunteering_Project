package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository <Enrollment, String> {


    Optional<Enrollment> findByEnrollmentId(Long enrollmentId);
}

