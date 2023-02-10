package com.team8.volunteerworkproject.service;


import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.entity.User;

import java.util.List;

public interface VolunteerWorkPostService {
    VolunteerWorkPostResponseDto createPost(VolunteerWorkPostRequestDto requestDto, User user);
    VolunteerWorkPostResponseDto updatePost(VolunteerWorkPostRequestDto requestDto, Long postId, User user);
    void deletePost(Long postId, VolunteerWorkPostRequestDto requestDto, User user);
    List<AllVolunteerWorkPostResponseDto> getAllPost();

    VolunteerWorkPostResponseDto getPost(Long postId);

}
