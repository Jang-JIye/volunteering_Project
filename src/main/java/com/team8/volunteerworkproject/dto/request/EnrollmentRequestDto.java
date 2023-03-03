package com.team8.volunteerworkproject.dto.request;
//Details.get(userId, name), postId, phoneNum,

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequestDto {

    private String userId;

    private String username;

    //양식에 안맞아도 통과해버림....
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "핸드폰 번호를 입력해 주세요.")
    private String phoneNumber;



}
