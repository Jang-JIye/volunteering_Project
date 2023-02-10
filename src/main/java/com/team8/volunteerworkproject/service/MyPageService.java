package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.ProfileRequestDto;
import com.team8.volunteerworkproject.dto.response.ProfileResponseDto;

public interface MyPageService {

  ProfileResponseDto createProfile(String userId, ProfileRequestDto requestDto);


}
