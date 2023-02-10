package com.team8.volunteerworkproject.service;


import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;

import java.util.List;

public interface VolunteerWorkPostService {

    List<AllVolunteerWorkPostResponseDto> getAllPost();

    VolunteerWorkPostResponseDto getPost(Long postId);

}
