package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.EnrollmentRequestDto;
import com.team8.volunteerworkproject.enums.EnrollmentStatus;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment extends  Timestamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String userId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus enrollmentStatus = EnrollmentStatus.TRUE;

    @Column
    private String username;

    public Enrollment(Long postId, EnrollmentRequestDto requestDto, String userId) {
        this.postId = requestDto.getPostId();
        this.userId = requestDto.getUserId();
    }
}
