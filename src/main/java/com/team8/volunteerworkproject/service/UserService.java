package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.SignupRequestDto;
import com.team8.volunteerworkproject.repository.UserRepository;

public interface UserService {

  void signup(SignupRequestDto requestDto);

}
