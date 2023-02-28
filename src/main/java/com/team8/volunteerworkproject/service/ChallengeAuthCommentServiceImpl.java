package com.team8.volunteerworkproject.service;


import com.team8.volunteerworkproject.dto.request.ChallengeAuthCommentRequestDto;
import com.team8.volunteerworkproject.dto.response.ChallengeAuthCommentResponseDto;
import com.team8.volunteerworkproject.entity.ChallengeAuth;
import com.team8.volunteerworkproject.entity.ChallengeAuthComment;
import com.team8.volunteerworkproject.repository.ChallengeAuthCommentRepository;
import com.team8.volunteerworkproject.repository.ChallengeAuthRepository;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeAuthCommentServiceImpl implements ChallengeAuthCommentService{


    private final ChallengeAuthCommentRepository challengeAuthCommentRepository;
    private final ChallengeAuthRepository challengeAuthRepository;

    //자랑글에 댓글
    @Override
    public ChallengeAuthCommentResponseDto createAuthComment(Long challengeAuthId, ChallengeAuthCommentRequestDto requestDto, UserDetailsImpl userDetails) {
        ChallengeAuth challengeAuth = challengeAuthRepository.findById(challengeAuthId).orElseThrow(
                ()-> new IllegalArgumentException("선택하신 글이 존재하지 않습니다.")
        );
        ChallengeAuthComment authComment = new ChallengeAuthComment(challengeAuth,  requestDto.getComment());

        challengeAuthCommentRepository.save(authComment);
        return new ChallengeAuthCommentResponseDto(authComment);
    }

    // 댓글 삭제
    @Override
    public void deleteAuthComment(Long challengeAuthId, UserDetailsImpl userDetails, Long challengeAuthCommentId) {
        challengeAuthRepository.findById(challengeAuthId).orElseThrow(
                ()-> new IllegalArgumentException("선택하신 자랑글이 존재하지 않습니다.")
        );

        ChallengeAuthComment challengeAuthComment = challengeAuthCommentRepository.findById(challengeAuthCommentId).orElseThrow(
                ()-> new IllegalArgumentException("삭제할 댓글이 없습니다.")
        );
        if(!userDetails.getUser().isValidId(challengeAuthComment.getUserId())){
            throw new IllegalArgumentException("삭제할 권한이 없습니다.");
        }
        challengeAuthCommentRepository.delete(challengeAuthComment);
    }

    //수정
    @Override
    public ChallengeAuthCommentResponseDto updateChallengeAuthComment(Long challengeAuthId, ChallengeAuthCommentRequestDto requestDto, Long challengeAuthCommentId, UserDetailsImpl userDetails) {
        challengeAuthRepository.findByChallengeAuthId(challengeAuthId).orElseThrow(
                ()-> new IllegalArgumentException("해당 챌린지 인증을 찾을 수 없습니다.")
        );

        ChallengeAuthComment challengeAuthComment = challengeAuthCommentRepository.findById(challengeAuthCommentId).orElseThrow(
                ()-> new IllegalArgumentException("수정할 댓글이 없습니다.")
        );

        if (!userDetails.getUser().isValidId(challengeAuthComment.getUserId())){
            throw new IllegalArgumentException("본인의 댓글만 수정할 수 있습니다.");
        }

        challengeAuthComment.updateChallengeAuthComment(requestDto);
        challengeAuthCommentRepository.save(challengeAuthComment);
        return new ChallengeAuthCommentResponseDto(challengeAuthComment);
    }

}

