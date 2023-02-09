package com.team8.volunteerworkproject.controller;


import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.service.VolunteerWorkPostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class VolunteerWorkPostController {

    private VolunteerWorkPostServiceImpl volunteerWorkPostService;

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
