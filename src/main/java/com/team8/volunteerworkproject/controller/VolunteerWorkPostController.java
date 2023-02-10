package com.team8.volunteerworkproject.controller;


import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.enums.UserRoleEnum;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.VolunteerWorkPostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class VolunteerWorkPostController {

    private VolunteerWorkPostServiceImpl volunteerWorkPostService;

    //게시글 생성
    //@Secured(UserRoleEnum.Authority.COMPANY)
    @Secured({"ROLE_COMPANY","ROLE_ADMIN"})
    @PostMapping("/volunteerWorkPosts")
    public VolunteerWorkPostResponseDto createPost(@RequestBody VolunteerWorkPostRequestDto requestDto,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return volunteerWorkPostService.createPost(requestDto, userDetails.getUsername());
    }

    //게시글 수정
    @PutMapping("/volunteerWorkPosts/{PostId}")


    //게시글 삭제
    @DeleteMapping("/volunteerWorkPosts/{PostId}")







    // 전제 모집글 조회
    @GetMapping("/volunteerWorkPosts")
    public ResponseEntity<List<AllVolunteerWorkPostResponseDto>> getAllPost(){
        return ResponseEntity.status(HttpStatus.OK).body(volunteerWorkPostService.getAllPost());
    }

    // 선택 모집글 조회
    @GetMapping("/volunteerWorkPosts/{voluteerWorkPostId}")
    public ResponseEntity<VolunteerWorkPostResponseDto> getPost(@PathVariable Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(volunteerWorkPostService.getPost(postId));
    }


}
