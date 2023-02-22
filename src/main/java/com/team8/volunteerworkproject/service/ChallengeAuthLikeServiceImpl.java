package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.entity.ChallengeAuth;
import com.team8.volunteerworkproject.entity.ChallengeAuthLike;
import com.team8.volunteerworkproject.repository.ChallengeAuthLikeRepository;
import com.team8.volunteerworkproject.repository.ChallengeAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeAuthLikeServiceImpl implements ChallengeAuthLikeService{

    private final ChallengeAuthLikeRepository challengeAuthLikeRepository;
    private final ChallengeAuthRepository challengeAuthRepository;

    @Override
    public void update(Long challengeAuthId, String userId) {
        ChallengeAuth challengeAuth = challengeAuthRepository.findById(challengeAuthId).orElseThrow(
                ()-> new IllegalArgumentException("해당글이 존재하지 않습니다.")
        );
        if(challengeAuthLikeRepository.findByChallengeAuthIdAndUserId(challengeAuthId,userId).isPresent()){
            challengeAuthLikeRepository.deleteByChallengeAuthIdAndUserId(challengeAuthId,userId);
        }else{
            challengeAuthLikeRepository.save(new ChallengeAuthLike(challengeAuthId,userId));
        }
    }

    @Override
    public int count(Long challengeAuthId) {
        List<ChallengeAuthLike> authLikes = challengeAuthLikeRepository.findAllByChallengeAuthId(challengeAuthId);
        return authLikes.size();
    }
}
