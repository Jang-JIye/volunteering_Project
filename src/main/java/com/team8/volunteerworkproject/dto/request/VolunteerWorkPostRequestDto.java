package com.team8.volunteerworkproject.dto.request;


import com.team8.volunteerworkproject.enums.PostStatus;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
public class VolunteerWorkPostRequestDto{
    private String title;

    private String content;
  //  private PostStatus postStatus;
    private String area;

    private String centerName; //세부 주소

    private String date; // 모집 날짜
}