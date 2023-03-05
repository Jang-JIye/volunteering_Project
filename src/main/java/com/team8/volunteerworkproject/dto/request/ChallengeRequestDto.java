package com.team8.volunteerworkproject.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class ChallengeRequestDto {


    private String userId;
    private String title;
    private String content;

    @Builder
    public ChallengeRequestDto(String userId, String title, String content, String image) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
