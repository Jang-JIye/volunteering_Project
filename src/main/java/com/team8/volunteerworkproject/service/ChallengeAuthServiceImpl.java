package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.ChallengeAuthRequestDto;
import com.team8.volunteerworkproject.dto.response.AllChallengeAuthResponseDto;
import com.team8.volunteerworkproject.dto.response.ChallengeAuthResponseDto;
import com.team8.volunteerworkproject.entity.ChallengeAuth;
import com.team8.volunteerworkproject.repository.ChallengeAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeAuthServiceImpl implements ChallengeAuthService {

    private final ChallengeAuthRepository challengeAuthRepository;
    private final ChallengeAuthLikeServiceImpl challengeAuthLikeService;

    //챌린지 자랑 동록
    @Override
    public ChallengeAuthResponseDto createChallengeAuth(ChallengeAuthRequestDto requestDto) {
        ChallengeAuth challengeAuth = new ChallengeAuth(requestDto);
        ChallengeAuth savedChallengeAuth = challengeAuthRepository.save(challengeAuth);
        return new ChallengeAuthResponseDto(savedChallengeAuth);
    }

    //챌린지 자랑 삭제
    @Override
    public void deleteChallengeAuth(Long challengeAuthId) {
        ChallengeAuth challengeAuth = challengeAuthRepository.findByChallengeAuthId(challengeAuthId).orElseThrow(
                ()-> new IllegalArgumentException("삭제할 챌린지 자랑이 존재하지 않습니다."));
        challengeAuthRepository.delete(challengeAuth);

    }

    //챌린지 자랑 전체 조회
    @Override
    @Transactional(readOnly = true)
    public List<AllChallengeAuthResponseDto> getAllChallengeAuth() {
        List<ChallengeAuth> challengeAuths = challengeAuthRepository.findAll();
        List<AllChallengeAuthResponseDto> responseDtos = new ArrayList<>();
        for(ChallengeAuth challengeAuth : challengeAuths){
            responseDtos.add(new AllChallengeAuthResponseDto(challengeAuth));
        }
        return responseDtos;
    }

    //챌린지 자랑 선택 조회
    @Override
    @Transactional(readOnly = true)
    public ChallengeAuthResponseDto getCahllengeAuth(Long challengeAuthId) {
        ChallengeAuth challengeAuth = challengeAuthRepository.findByChallengeAuthId(challengeAuthId).orElseThrow(
                ()-> new IllegalArgumentException("찾으시는 챌린지 자랑 글이 없습니다.")
        );
        int likeNum = challengeAuthLikeService.count(challengeAuthId);
        ChallengeAuthResponseDto responseDto = new ChallengeAuthResponseDto(challengeAuth, likeNum);
        return responseDto;
    }

}
