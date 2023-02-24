package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.ChallengeAuthRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "challengeAuth", cascade = CascadeType.ALL)
    private List<ChallengeAuthComment> comment;
    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int likeNum;


    public ChallengeAuth(String userId, String title, String content, String image) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
