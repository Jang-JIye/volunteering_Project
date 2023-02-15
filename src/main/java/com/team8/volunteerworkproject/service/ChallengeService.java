package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.ChallengeRequestDto;
import com.team8.volunteerworkproject.dto.response.ChallengeResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface ChallengeService {

    ChallengeResponseDto createChallenge(ChallengeRequestDto requestDto);

    ChallengeResponseDto updateChallenge(@PathVariable Long ChallengeId, ChallengeRequestDto requestDto);

    void deleteChallenge(@PathVariable Long challengeId);

}
