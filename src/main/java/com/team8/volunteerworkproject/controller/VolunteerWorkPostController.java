package com.team8.volunteerworkproject.controller;


import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusResponseDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.enums.UserRoleEnum;
import com.team8.volunteerworkproject.enums.UserRoleEnum.Authority;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.S3Service;
import com.team8.volunteerworkproject.service.VolunteerWorkPostServiceImpl;
import java.io.IOException;
import java.nio.charset.Charset;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
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
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class VolunteerWorkPostController {

  private final VolunteerWorkPostServiceImpl volunteerWorkPostService;
  private final S3Service s3Service;
  private static String dirName = "volunteerWorkPost";
  private static String imgPath = "volunteerWorkPost/volunteerWorkPost-basic.jpg";

  //게시글 작성
  @Secured(UserRoleEnum.Authority.COMPANY)
  @PostMapping("/volunteerWorkPosts")
  public ResponseEntity<StatusResponseDto> createPost(
      @RequestPart("requestDto") VolunteerWorkPostRequestDto requestDto,
      @RequestPart(value = "file", required = false) MultipartFile file,
      @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
    if(file == null){
      imgPath = imgPath;
    }else {
      imgPath = s3Service.updateImage(file, dirName);
    }
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "봉사모집글이 작성되었습니다.");
    volunteerWorkPostService.createPost(userDetails.getUserId(), requestDto, imgPath);
    HttpHeaders headers = new HttpHeaders();//필추
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));//필추

    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    // new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  //게시글 수정
  @Secured(UserRoleEnum.Authority.COMPANY)
  @PatchMapping("/volunteerWorkPosts/{postId}")
  public ResponseEntity<StatusAndDataResponseDto> updatePost(
      @RequestPart("requestDto") VolunteerWorkPostRequestDto requestDto,
      @RequestPart(value = "file", required = false) MultipartFile file,
      @PathVariable Long postId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
    if(file == null){
      imgPath = volunteerWorkPostService.getPostImage(userDetails.getUserId(), postId);
    }else {
      imgPath = s3Service.updateImage(file, dirName);
    }
    VolunteerWorkPostResponseDto data = volunteerWorkPostService.updatePost(requestDto, postId,
        userDetails.getUserId(), imgPath);
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "봉사모집글이 수정되었습니다.", data);

    HttpHeaders headers = new HttpHeaders();//필추
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));//필추

    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }


  //게시글 삭제
  @Secured({UserRoleEnum.Authority.COMPANY, Authority.ADMIN})
  @DeleteMapping("/volunteerWorkPosts/{postId}")
  public ResponseEntity<StatusResponseDto> deletePost(@PathVariable Long postId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "해당 봉사모집글이 삭제되었습니다.");
    volunteerWorkPostService.deletePost(postId, userDetails.getUserId());

    HttpHeaders headers = new HttpHeaders();//필추
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));//필추

    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);//responseDto
  }
//-----------------------------------------------------------------------------------------------------------------------

  // 전체 모집글 조회
  @GetMapping("/volunteerWorkPosts")
  public ResponseEntity<StatusAndDataResponseDto> getAllPost() {
    List<AllVolunteerWorkPostResponseDto> data = volunteerWorkPostService.getAllPost();

    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "전체 봉사모집글 조회가 완료되었습니다.", data);

    HttpHeaders headers = new HttpHeaders();//필추
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));//필추
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  // 선택 모집글 조회
  @GetMapping("/volunteerWorkPosts/{postId}")
  public ResponseEntity<StatusAndDataResponseDto> getPost(@PathVariable Long postId) {
    VolunteerWorkPostResponseDto data = volunteerWorkPostService.getPost(postId);
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "선택한 봉사모집글 조회가 완료되었습니다.", data);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }


}
