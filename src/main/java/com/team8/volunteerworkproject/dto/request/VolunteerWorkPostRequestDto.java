package com.team8.volunteerworkproject.dto.request;


import com.team8.volunteerworkproject.enums.PostStatus;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Builder
public class VolunteerWorkPostRequestDto{

    private String title;

    private String content;
  //  private PostStatus postStatus;
    private String area;

    private String centerName; //세부 주소

    private LocalDateTime endTime; //마감 날짜

    private int maxEnrollmentNum;

    //모집인원(maxEnrollmentNum)
}