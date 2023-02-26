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
public class Enrollment extends  Timestamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus enrollmentStatus = EnrollmentStatus.TRUE;

    private String userId;

    @Column(nullable = false)
    private String username;

    @Column
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private VolunteerWorkPost post;




    public Enrollment(Long postId, EnrollmentRequestDto requestDto, String userId, VolunteerWorkPost post) {
        /*this.postId = postId;*/
        this.userId = userId;
        this.username = requestDto.getUsername();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.post = post;
    }
}
