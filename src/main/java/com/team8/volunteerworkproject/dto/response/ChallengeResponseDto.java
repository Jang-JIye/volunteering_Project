package com.team8.volunteerworkproject.dto.response;


import com.team8.volunteerworkproject.entity.Challenge;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChallengeResponseDto {

    private Long challengeId;
    private String title;
    private String content;
    private String image;

    public ChallengeResponseDto(){

    }

    public ChallengeResponseDto(Long challengeId, String title, String content, String image) {
        this.challengeId = challengeId;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public ChallengeResponseDto(Challenge challenge) {
        this.challengeId = challenge.getChallengeId();
        this.title = challenge.getTitle();
        this.content = challenge.getContent();
        this.image = challenge.getImage();
    }
}
