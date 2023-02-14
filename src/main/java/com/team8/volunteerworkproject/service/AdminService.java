package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.NoticeRequestDto;
import com.team8.volunteerworkproject.dto.response.NoticeResponseDto;

import java.util.List;


public interface AdminService {

    NoticeResponseDto createNotice(String userId, NoticeRequestDto requestDto);

    List<NoticeResponseDto> getNoticeList();

    NoticeResponseDto findNotice(Long id ,String userId);

    NoticeResponseDto updateNotice(Long id,NoticeRequestDto requestDto,String userId);

    NoticeResponseDto deleteNotice(Long id ,String userId);


}
