package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.NoticeRequestDto;
import com.team8.volunteerworkproject.dto.response.NoticeResponseDto;

import java.util.List;


public interface AdminService {

    NoticeResponseDto createNotice(String userId, NoticeRequestDto requestDto);

    List<NoticeResponseDto> getNoticeList();

    NoticeResponseDto findNotice(Long noticeId ,String userId);

    NoticeResponseDto updateNotice(Long noticeId,NoticeRequestDto requestDto,String userId);

    void deleteNotice(Long noticeId , String userId);

    void adminDeletePost(Long postId);

    void adminDeleteComment(Long postId,Long commentId);


}
