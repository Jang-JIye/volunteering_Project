package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.ChallengeRequestDto;
import com.team8.volunteerworkproject.dto.response.ChallengeResponseDto;
import com.team8.volunteerworkproject.entity.Challenge;
import com.team8.volunteerworkproject.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;

    //챌린지 작성
    @Override
    public ChallengeResponseDto createChallenge(ChallengeRequestDto requestDto) {
        Challenge challenge = new Challenge(requestDto);
        Challenge savedChallenge = challengeRepository.save(challenge);
        return new ChallengeResponseDto(savedChallenge);
    }

    //챌린지 수정
    @Override
    public ChallengeResponseDto updateChallenge(Long challengeId, ChallengeRequestDto requestDto) {
        Challenge challenge = challengeRepository.findByChallengeId(challengeId).orElseThrow(
                ()-> new IllegalArgumentException("수정할 챌린지가 없습니다."));

        challenge.update(requestDto.getTitle(), requestDto.getContent());
        Challenge savedChallenge = challengeRepository.save(challenge);
        return new ChallengeResponseDto(savedChallenge);
    }

    //챌린지 삭제
    @Override
    public void deleteChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findByChallengeId(challengeId).orElseThrow(
                () -> new IllegalArgumentException("삭제할 챌린지가 존재하지 않습니다."));

        challengeRepository.delete(challenge);

    }
}