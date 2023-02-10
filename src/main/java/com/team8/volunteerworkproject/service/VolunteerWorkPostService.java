package com.team8.volunteerworkproject.service;


import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.security.UserDetailsImpl;

import java.util.List;

public interface VolunteerWorkPostService {
    VolunteerWorkPostResponseDto createPost(VolunteerWorkPostRequestDto requestDto, UserDetailsImpl userDetails);
    VolunteerWorkPostResponseDto updatePost(VolunteerWorkPostRequestDto requestDto, Long postId, UserDetailsImpl userDetails);
    VolunteerWorkPostResponseDto deletePost(Long postId, UserDetailsImpl userDetails);
    List<AllVolunteerWorkPostResponseDto> getAllPost();

    VolunteerWorkPostResponseDto getPost(Long postId);

}
