package com.team8.volunteerworkproject.service;


import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;

import java.util.List;

public interface VolunteerWorkPostService {

    List<VolunteerWorkPostResponseDto> getAllPost();

    VolunteerWorkPostResponseDto getPost(Long postId);

}
