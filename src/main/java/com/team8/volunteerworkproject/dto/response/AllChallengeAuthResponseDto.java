package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.ChallengeAuth;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AllChallengeAuthResponseDto {

    private Long challengeAuthId;
    private String title;
    private String content;
    private int likeNum;

    public AllChallengeAuthResponseDto(ChallengeAuth challengeAuth) {
        this.challengeAuthId = challengeAuth.getChallengeAuthId();
        this.title = challengeAuth.getTitle();
        this.content = challengeAuth.getContent();
        this.likeNum = challengeAuth.getLikeNum();
    }
}
