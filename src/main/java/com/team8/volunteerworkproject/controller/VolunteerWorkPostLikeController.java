package com.team8.volunteerworkproject.controller;


import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.VolunteerWorkPostLikeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VolunteerWorkPostLikeController {

    private final VolunteerWorkPostLikeServiceImpl volunteerWorkPostLikeService;

    //좋아요 등록 & 취소
    @PostMapping("/voluteerWorkPosts/{postId}/likes")
    private void update(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        volunteerWorkPostLikeService.update(postId,userDetails.getUserId());
    }


}
