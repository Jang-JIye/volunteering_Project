package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.common.RedisDao;
import com.team8.volunteerworkproject.dto.request.ProfileRequestDto;
import com.team8.volunteerworkproject.dto.request.PwcheckRequestDto;
import com.team8.volunteerworkproject.dto.request.SigninRequestDto;
import com.team8.volunteerworkproject.dto.request.SignupRequestDto;
import com.team8.volunteerworkproject.dto.response.ProfileResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.jwt.AuthenticatedUserInfoDto;
import com.team8.volunteerworkproject.jwt.JwtUtil;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.nio.charset.Charset;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final JwtUtil jwtUtil;
  private final RedisDao redisDao;

  //회원가입
  @PostMapping("/users/signup")
  public ResponseEntity<StatusResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto) {
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "회원가입이 완료되었습니다.");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    userService.signup(requestDto);
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  //로그인
  @PostMapping("/users/signin")
  public ResponseEntity<StatusResponseDto> signin(@RequestBody @Valid SigninRequestDto requestDto,
      HttpServletResponse response) {
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "로그인이 완료되었습니다.");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));

    AuthenticatedUserInfoDto userInfoDto = userService.signin(requestDto);
    String accessToken = jwtUtil.createToken(userInfoDto.getUsername(), userInfoDto.getRole());
    String refreshToken = jwtUtil.createRefreshToken(userInfoDto.getUsername(),
        userInfoDto.getRole());
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
    redisDao.setValues(userInfoDto.getUsername(), refreshToken, Duration.ofMinutes(10));

    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  //로그아웃
  @PostMapping("/users/signout")
  public ResponseEntity<StatusResponseDto> signout(
      @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response) {
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "로그아웃이 완료되었습니다.");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    redisDao.deleteValues(userDetails.getUserId());
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, null);
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  //회원탈퇴
  @PatchMapping("users/unregister")
  public ResponseEntity<StatusResponseDto> updateProfile(@RequestBody PwcheckRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    userService.unregister(userDetails.getUserId(), requestDto);
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "회원탈퇴가 완료되었습니다.");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

}
