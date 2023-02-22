package com.team8.volunteerworkproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ChallengeAuthLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ChallengeAuthLikeId;

    @Column(nullable = false)
    private Long challengeAuthId;

    @Column(nullable = false)
    private String userId;

    public ChallengeAuthLike(Long challengeAuthId, String userId) {
        this.challengeAuthId = challengeAuthId;
        this.userId = userId;
    }
}
