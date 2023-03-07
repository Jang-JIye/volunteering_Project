package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.ProfileRequestDto;
import com.team8.volunteerworkproject.dto.response.ProfileResponseDto;
import com.team8.volunteerworkproject.security.UserDetailsImpl;

public interface ProfileService {

//  ProfileResponseDto createProfile(String userId, ProfileRequestDto requestDto);

  ProfileResponseDto getCustomerProfile(String userId);
  String getProfileImage(String userId);
  ProfileResponseDto updateProfile(String userId, ProfileRequestDto requestDto, String imgPath);




}

