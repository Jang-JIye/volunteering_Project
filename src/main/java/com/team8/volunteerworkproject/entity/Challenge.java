package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.ChallengeRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Challenge extends Timestamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String image;


    public Challenge(String userId, String title, String content, String image) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public void update(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
