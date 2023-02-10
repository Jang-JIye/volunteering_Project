package com.team8.volunteerworkproject.controller;


import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusResponseDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.enums.UserRoleEnum;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.VolunteerWorkPostServiceImpl;
import java.nio.charset.Charset;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    private final VolunteerWorkPostServiceImpl volunteerWorkPostService;

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
    public ResponseEntity<StatusAndDataResponseDto> getAllPost(){
        List<AllVolunteerWorkPostResponseDto> data = volunteerWorkPostService.getAllPost();
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "전체 모집글 조회가 완료되었습니다.", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    // 선택 모집글 조회
    @GetMapping("/volunteerWorkPosts/{postId}")
    public ResponseEntity<StatusAndDataResponseDto> getPost(@PathVariable Long postId){
       VolunteerWorkPostResponseDto data = volunteerWorkPostService.getPost(postId);
        StatusAndDataResponseDto responseDto = new  StatusAndDataResponseDto(StatusEnum.OK, "선택 모집글 조회가 완료되었습니다.", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }


}
