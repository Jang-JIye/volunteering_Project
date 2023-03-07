package com.team8.volunteerworkproject.controller;


import com.team8.volunteerworkproject.dto.request.ProfileRequestDto;
import com.team8.volunteerworkproject.dto.response.ProfileResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.repository.ProfileRepository;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.ProfileService;
import com.team8.volunteerworkproject.service.S3Service;
import java.io.IOException;
import java.nio.charset.Charset;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor

public class ProfileController {

  private final ProfileService profileService;
  private final S3Service s3Service;
  private static String dirName = "profile";
  private static String imgPath;

//  @PostMapping("/profiles")
//  public ResponseEntity<StatusAndDataResponseDto> createProfile(@RequestBody ProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//    ProfileResponseDto data = profileService.createProfile(userDetails.getUserId(), requestDto);
//    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "프로필 작성이 완료되었습니다.", data);
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
//    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
//  }

  @GetMapping("/mypage/profiles")
  public ResponseEntity<StatusAndDataResponseDto> getCustomerProfileByUserId(
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    ProfileResponseDto data = profileService.getCustomerProfile(userDetails.getUserId());
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "프로필 조회 완료되었습니다.", data);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  @PatchMapping("/mypage/profiles")
  public ResponseEntity<StatusAndDataResponseDto> profileImage(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestPart ProfileRequestDto requestDto,
      @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
    if(file == null){
      imgPath = profileService.getProfileImage(userDetails.getUserId());
    }else {
      imgPath = s3Service.updateImage(file, dirName);
    }
    ProfileResponseDto data = profileService.updateProfile(userDetails.getUserId(), requestDto,
        imgPath);
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "프로필이 수정되었습니다.", data);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }


}
