package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.enums.PostStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


  public VolunteerWorkPost(String userId, String title, String content,
                           String area, String centerName) {
    this.userId = userId;
    this.title = title;
    //this.postStatus = postStatus;
    this.content = content;
    this.area = area;

    this.centerName = centerName;
  }

//    @Column(nullable = false)
//    private LocalDateTime schedule;


  public void update(VolunteerWorkPostRequestDto requestDto) {//지역, 상태,
    this.title = requestDto.getTitle();
    this.content = requestDto.getContent();
   // this.postStatus = requestDto.getPostStatus();//상태
    this.area = requestDto.getArea();
  }

}
