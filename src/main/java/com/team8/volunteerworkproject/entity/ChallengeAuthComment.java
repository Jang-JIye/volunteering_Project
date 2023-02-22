package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.ChallengeAuthCommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ChallengeAuthComment extends Timestamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ChallengeAuthCommentId;

    @Column(nullable = false)
    private Long challengeAuthId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userId;


    public ChallengeAuthComment(ChallengeAuth challengeAuth) {
        this.content = challengeAuth.getContent();
        this.userId = challengeAuth.getUserId();
        this.challengeAuthId = challengeAuth.getChallengeAuthId();
    }
}
