package com.team8.volunteerworkproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeRequestDto {


    private String userId;
    private String title;
    private String content;


}
