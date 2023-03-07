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
    private String title;
    private String area;
    private String username;
    private String enrollmentStatus;
    private Long enrollmentId;
    private String phoneNumber;

    public EnrollmentResponseDto(Enrollment enrollment) {
        this.postId = enrollment.getPost().getPostId();
        this.userId = enrollment.getUserId();
        this.title = enrollment.getPost().getTitle();
        this.area = enrollment.getPost().getArea();
        this.enrollmentId = enrollment.getEnrollmentId();
        this.username = enrollment.getUsername();
        this.enrollmentStatus = String.valueOf(enrollment.getEnrollmentStatus());
        this.phoneNumber = enrollment.getPhoneNumber();
    }
}
