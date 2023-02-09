package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.common.RedisDAO;
import com.team8.volunteerworkproject.dto.request.SignupRequestDto;
import com.team8.volunteerworkproject.dto.response.StatusResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.jwt.JwtUtil;
import com.team8.volunteerworkproject.service.UserService;
import jakarta.validation.Valid;
import java.nio.charset.Charset;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final JwtUtil jwtUtil;
  private final RedisDAO redisDAO;

  @PostMapping("/users/signup")
  public ResponseEntity<StatusResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto) {
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "회원가입이 완료되었습니다.");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    userService.signup(requestDto);
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

}
