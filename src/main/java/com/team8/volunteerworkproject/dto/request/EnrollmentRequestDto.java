package com.team8.volunteerworkproject.dto.request;
//Details.get(userId, name), postId, phoneNum,

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequestDto {

    private Long postId;
    private String userId;
    private String username;
//    private int phoneNum;



}
