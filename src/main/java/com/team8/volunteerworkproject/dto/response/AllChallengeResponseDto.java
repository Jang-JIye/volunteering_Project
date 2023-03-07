package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.Challenge;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AllChallengeResponseDto {

    private Long challengeId;
    private String title;
    private String content;

    public AllChallengeResponseDto(Challenge challenge) {
        this.challengeId = challenge.getChallengeId();
        this.title = challenge.getTitle();
        this.content = challenge.getContent();
    }
}
