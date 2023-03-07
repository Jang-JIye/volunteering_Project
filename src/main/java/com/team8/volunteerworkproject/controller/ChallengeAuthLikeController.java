package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.ChallengeAuthLikeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChallengeAuthLikeController {

    private final ChallengeAuthLikeServiceImpl challengeAuthLikeService;

    @PostMapping("challenge-auth/{challengeAuthId}/likes")
    private void update(@PathVariable Long challengeAuthId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        challengeAuthLikeService.update(challengeAuthId,userDetails.getUserId());
    }

}
