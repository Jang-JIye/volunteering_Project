package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.ChallengeRequestDto;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

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


    public Challenge(ChallengeRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
