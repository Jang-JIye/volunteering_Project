package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import lombok.Getter;


@Getter
public class VolunteerWorkPostResponseDto {

  private Long postId;
  private String title;
  private String userId;
  private String interestArea;
  private String status;
  private String contents;
//    private LocalDateTime schedule;


  public VolunteerWorkPostResponseDto(VolunteerWorkPost post) {
    this.postId = post.getId();
    this.title = post.getTitle();
    this.userId = post.getUser().getUserId();
    this.interestArea = post.getInterestArea();
    this.status = post.getPostStatus();
    this.contents = post.getcontents();
//        this.schedule = post.getSchedule();

  }
}
