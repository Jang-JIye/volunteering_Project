package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.ChallengeAuthRequestDto;

import com.team8.volunteerworkproject.dto.response.AllChallengeAuthResponseDto;
import com.team8.volunteerworkproject.dto.response.ChallengeAuthResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface ChallengeAuthService {

    ChallengeAuthResponseDto createChallengeAuth(ChallengeAuthRequestDto requestDto);
    void deleteChallengeAuth (@PathVariable Long challengeAuthId);
    List<AllChallengeAuthResponseDto> getAllChallengeAuth();
    ChallengeAuthResponseDto getCahllengeAuth(Long challengeAuthId);



}
