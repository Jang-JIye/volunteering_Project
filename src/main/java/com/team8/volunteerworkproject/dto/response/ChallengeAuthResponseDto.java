package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.ChallengeAuth;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChallengeAuthResponseDto {
    private Long challengeAuthId;
    private String title;
    private String content;
    private String image;
    private int likeNum;

    public ChallengeAuthResponseDto(ChallengeAuth challengeAuth) {
        this.challengeAuthId = challengeAuth.getChallengeAuthId();
        this.title = challengeAuth.getTitle();
        this.content = challengeAuth.getContent();
        this.image = challengeAuth.getImage();
    }

    public ChallengeAuthResponseDto(ChallengeAuth challengeAuth, int likeNum) {
        this.challengeAuthId = challengeAuth.getChallengeAuthId();
        this.title = challengeAuth.getTitle();
        this.content = challengeAuth.getContent();
        this.image = challengeAuth.getImage();
        this.likeNum = likeNum;
    }
}
