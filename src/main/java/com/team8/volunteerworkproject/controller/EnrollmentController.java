package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.EnrollmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class EnrollmentController {
    private final EnrollmentServiceImpl enrollmentService;

    //참여 신청 & 취소
    @PostMapping("/volunteerWorkPosts/{postId}/participations")
    public void attend(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        enrollmentService.attend(postId, userDetails.getUserId());
    }
    @DeleteMapping("/volunteerWorkPosts/{postId}/participations")
    public void cancel(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        enrollmentService.cancel(postId, userDetails.getUserId());
    }

}
