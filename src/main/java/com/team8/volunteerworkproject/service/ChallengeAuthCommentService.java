package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.ChallengeAuthCommentRequestDto;
import com.team8.volunteerworkproject.dto.response.ChallengeAuthCommentResponseDto;

import com.team8.volunteerworkproject.security.UserDetailsImpl;

import java.util.List;

public interface ChallengeAuthCommentService {

    //댓글 작성
    ChallengeAuthCommentResponseDto createAuthComment(Long challengeAuthId, ChallengeAuthCommentRequestDto requestDto, UserDetailsImpl userDetails);

    //댓글 삭제
    void deleteAuthComment(Long challengeAuthId,UserDetailsImpl userDetails, Long challengeAuthCommentId);

    //댓글 수정
    ChallengeAuthCommentResponseDto updateChallengeAuthComment(Long challengeAuthId,ChallengeAuthCommentRequestDto requestDto, Long challengeAuthCommentId,UserDetailsImpl userDetails);

}
