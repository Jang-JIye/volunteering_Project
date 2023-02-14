package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.ApplicationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class ApplicationController {
    private final ApplicationServiceImpl applicationService;

    //참여 신청 & 취소
    @PutMapping("/volunteerWorkPosts/{postId}/participations")
    public void enrollment(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        applicationService.enrollment(postId, userDetails.getUserId());
    }
}
