package com.team8.volunteerworkproject.dto.response;
//Details.get(userId. name), phoneNum,

import com.team8.volunteerworkproject.entity.Enrollment;
import com.team8.volunteerworkproject.enums.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponseDto {
    private Long postId;
    private String userId;
    private String username;
    private String enrollmentStatus;

    public EnrollmentResponseDto(Enrollment enrollment) {
        this.postId = enrollment.getPostId();
        this.userId = enrollment.getUserId();
        this.username = enrollment.getUsername();
        this.enrollmentStatus = String.valueOf(enrollment.getEnrollmentStatus());
    }
//    private int phoneNum;
}
