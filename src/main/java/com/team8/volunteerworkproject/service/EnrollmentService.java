package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.EnrollmentRequestDto;
import com.team8.volunteerworkproject.dto.response.EnrollmentResponseDto;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import org.springframework.transaction.annotation.Transactional;

public interface EnrollmentService {

/*
    void participation(Long postId, String userId);
*/
    //참가 신청
    EnrollmentResponseDto attend(Long postId, EnrollmentRequestDto requestDto, UserDetailsImpl userDetails, Long enrollmentId);

    //참가 신청 취소(삭제)
    EnrollmentResponseDto cancel(Long postId, UserDetailsImpl userDetails, Long enrollmentId);
}
