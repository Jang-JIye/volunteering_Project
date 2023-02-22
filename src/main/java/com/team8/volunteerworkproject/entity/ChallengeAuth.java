package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.ChallengeAuthRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ChallengeAuth extends Timestamp{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeAuthId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int likeNum;

    public ChallengeAuth(ChallengeAuth challengeAuth) {
        this.challengeAuthId = challengeAuth.getChallengeAuthId();
        this.userId = challengeAuth.getUserId();
        this.title = challengeAuth.getTitle();
        this.content = challengeAuth.getContent();
        this.image = challengeAuth.getImage();
    }

    public ChallengeAuth(ChallengeAuthRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.image = requestDto.getImage();
    }
}
