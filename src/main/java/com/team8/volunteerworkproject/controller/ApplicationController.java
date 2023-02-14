package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.ApplicationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class ApplicationController {
    private final ApplicationServiceImpl applicationService;

    //참여 신청 & 취소
    @PostMapping("/volunteerWorkPosts/{postId}/participations")
    public void attend(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        applicationService.attend(postId, userDetails.getUserId());
    }
    @DeleteMapping("/volunteerWorkPosts/{postId}/participations")
    public void cancel(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        applicationService.cancel(postId, userDetails.getUserId());
    }

}
