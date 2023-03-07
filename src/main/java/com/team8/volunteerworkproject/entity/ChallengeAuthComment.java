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

    @ManyToOne
    @JoinColumn(name = "challenge_auth_id")
    private ChallengeAuth challengeAuth;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String userId;



    public ChallengeAuthComment(ChallengeAuth challengeAuth, String comment) {
        this.comment = comment;
        this.userId = challengeAuth.getUserId();
        this.challengeAuth = challengeAuth;
    }

    public void updateChallengeAuthComment(ChallengeAuthCommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}
