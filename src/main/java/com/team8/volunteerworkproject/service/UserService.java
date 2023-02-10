package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.SigninRequestDto;
import com.team8.volunteerworkproject.dto.request.SignupRequestDto;
import com.team8.volunteerworkproject.jwt.AuthenticatedUserInfoDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

  void signup(SignupRequestDto requestDto);
  AuthenticatedUserInfoDto signin(SigninRequestDto requestDto);

  void signout(HttpServletRequest request);

}
