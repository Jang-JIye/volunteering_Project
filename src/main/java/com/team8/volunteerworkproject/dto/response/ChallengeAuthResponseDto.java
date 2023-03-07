package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.ChallengeAuth;
import com.team8.volunteerworkproject.entity.ChallengeAuthComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChallengeAuthResponseDto {
    private Long challengeAuthId;
    private String title;
    private String content;
    private String image;
    private int likeNum;
    private List<ChallengeAuthComment> commentList = new ArrayList<>();

    public ChallengeAuthResponseDto(ChallengeAuth challengeAuth) {
        this.challengeAuthId = challengeAuth.getChallengeAuthId();
        this.title = challengeAuth.getTitle();
        this.content = challengeAuth.getContent();
        this.image = challengeAuth.getImage();

    }

    public ChallengeAuthResponseDto(ChallengeAuth challengeAuth, int likeNum, List<ChallengeAuthComment> comments) {
        this.challengeAuthId = challengeAuth.getChallengeAuthId();
        this.title = challengeAuth.getTitle();
        this.content = challengeAuth.getContent();
        this.image = challengeAuth.getImage();
        for(ChallengeAuthComment comment : comments){
            this.commentList.add(comment);
        }
        this.likeNum = likeNum;
    }
}
