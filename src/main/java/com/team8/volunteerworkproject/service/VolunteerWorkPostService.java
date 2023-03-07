package com.team8.volunteerworkproject.service;


import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;

import java.util.List;

public interface VolunteerWorkPostService {

    VolunteerWorkPostResponseDto createPost(String userId, VolunteerWorkPostRequestDto requestDto, String imgPath);
    VolunteerWorkPostResponseDto updatePost(VolunteerWorkPostRequestDto requestDto, Long postId,  String userId, String imgPath);
    String getPostImage(String userId, Long postId);
    void deletePost(Long postId, String userId);
    List<AllVolunteerWorkPostResponseDto> getAllPost();
    VolunteerWorkPostResponseDto getPost(Long postId);


}
