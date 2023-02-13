package com.team8.volunteerworkproject.dto.request;


import com.team8.volunteerworkproject.enums.StatusEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
//@NoArgsConstructor //빌더 안에 기능이 있다.
@Builder
public class VolunteerWorkPostRequestDto {
    private Long postId;
    private String userId;

    private String title;
    private String contents;
    private StatusEnum postStatus;
    private String area;

    private String nickname;

    private Long id;

}
