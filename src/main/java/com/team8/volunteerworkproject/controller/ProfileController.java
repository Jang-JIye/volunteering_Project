package com.team8.volunteerworkproject.controller;


import com.team8.volunteerworkproject.dto.request.ProfileRequestDto;
import com.team8.volunteerworkproject.dto.response.ProfileResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.ProfileService;
import java.nio.charset.Charset;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class ProfileController {

  private final ProfileService profileService;

//  @PostMapping("/profiles")
//  public ResponseEntity<StatusAndDataResponseDto> createProfile(@RequestBody ProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//    ProfileResponseDto data = profileService.createProfile(userDetails.getUserId(), requestDto);
//    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "프로필 작성이 완료되었습니다.", data);
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
//    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
//  }

  @GetMapping("/profiles")
  public ResponseEntity<StatusAndDataResponseDto> getCustomerProfileByUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    ProfileResponseDto data = profileService.getCustomerProfile(userDetails.getUserId());
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "프로필 조회 완료되었습니다.", data);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  @PatchMapping("/profiles")
  public ResponseEntity<StatusAndDataResponseDto> updateProfile(@RequestBody ProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    ProfileResponseDto data = profileService.updateProfile(userDetails, requestDto);
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "프로필 수정이 완료되었습니다.", data);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

}
