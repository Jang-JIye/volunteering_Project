package com.team8.volunteerworkproject.entity;

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

//    @Column(nullable = false)
//    private LocalDateTime schedule;


}
