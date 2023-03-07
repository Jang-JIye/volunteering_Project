package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.ChallengeRequestDto;
import com.team8.volunteerworkproject.dto.response.AllChallengeResponseDto;
import com.team8.volunteerworkproject.dto.response.ChallengeResponseDto;
import com.team8.volunteerworkproject.entity.Challenge;
import com.team8.volunteerworkproject.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;

    //챌린지 작성
    @Override
    public ChallengeResponseDto createChallenge(ChallengeRequestDto requestDto) {
        Challenge challenge = new Challenge(requestDto.getUserId(), requestDto.getTitle(),
            requestDto.getContent(), requestDto.getImage());
        Challenge savedChallenge = challengeRepository.save(challenge);
        return new ChallengeResponseDto(savedChallenge);
    }

    //챌린지 수정
    @Override
    public ChallengeResponseDto updateChallenge(Long challengeId, ChallengeRequestDto requestDto) {
        Challenge challenge = challengeRepository.findByChallengeId(challengeId).orElseThrow(
                ()-> new IllegalArgumentException("수정할 챌린지가 없습니다."));

        challenge.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getImage());
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

    //챌린지 전체 조회
    @Override
    @Transactional(readOnly = true)
    public List<AllChallengeResponseDto> getAllChallenge() {
        List<Challenge> challengeList = challengeRepository.findAll();
        List<AllChallengeResponseDto> responseDtoList = new ArrayList<>();
        for (Challenge challenge : challengeList){
            responseDtoList.add(new AllChallengeResponseDto(challenge));
        }
        return responseDtoList;
    }

    //챌린지 선택 조회
    @Override
    @Transactional(readOnly = true)
    public ChallengeResponseDto getChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findByChallengeId(challengeId).orElseThrow(
                ()-> new IllegalArgumentException("찾으시는 챌린지가 없습니다."));
        ChallengeResponseDto responseDto = new ChallengeResponseDto(challenge);
        return responseDto;
    }
}
