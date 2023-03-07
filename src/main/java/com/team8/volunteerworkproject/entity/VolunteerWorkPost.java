package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.enums.PostStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Entity
@NoArgsConstructor
public class VolunteerWorkPost extends Timestamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;
  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false)
  private PostStatus postStatus = PostStatus.TRUE;

  @Column(nullable = false)
  private String userId;

  @Column(nullable = false)
  private String title;


  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String area;

  @Column
  private String nickname;

  @Column
  private String centerName;

  @Column
  private LocalDateTime endTime;

  @Column(nullable = false)
  private String image;

  //모집 날짜가 지나면 모집완료로 변경
  @PreUpdate
  public void beforeUpdate() {
    if (LocalDateTime.now().isAfter(getEndTime())) {
      postStatus = PostStatus.FALSE;
    }
    else {
      postStatus = PostStatus.TRUE;
    }
  }

  @Column
  private int maxEnrollmentNum;

  //모집 날짜 지난 경우 선택 불가
  @PrePersist
  public void checkEndTime() {
    if (endTime.isBefore(LocalDateTime.now())) {
      throw new IllegalArgumentException("모집기간이 이미 지났습니다.");
    }
  }


  public VolunteerWorkPost(String userId, String title, String content,
                           String area, String centerName, LocalDateTime endTime, int maxEnrollmentNum, String image) {
    this.userId = userId;
    this.title = title;
    this.content = content;
    this.area = area;

    this.centerName = centerName;
    this.endTime = endTime;
    this.maxEnrollmentNum = maxEnrollmentNum;
    this.image = image;
  }

  public void update(VolunteerWorkPostRequestDto requestDto, String imgPath) {//지역, 상태,
    this.title = requestDto.getTitle();
    this.content = requestDto.getContent();
    this.area = requestDto.getArea();
    this.endTime = requestDto.getEndTime();
    this.image = imgPath;
  }

}
