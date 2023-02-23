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
        challengeAuthCommentRepository.findById(challengeAuthId).orElseThrow(
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
}

