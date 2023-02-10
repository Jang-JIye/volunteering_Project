package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.CommentRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Column(nullable = false)
  private String contents;

  //연관관계 끊기
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  //연관관계 끊기
  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private VolunteerWorkPost volunteerWorkPost;

  public boolean isWriter(String userId) {
    return this.user.isValidUserId(userId) || this.user.isAdmin();
  }

//  // 작성자 검증을 어떻게 해야 할지? 기존 프로젝트에서는 40~48번 줄을 User엔티티에 입력 했음.
//  public boolean isValidUserId(Long userId) {
//    return this.id.equals(userId);
//  }
//
//  public boolean isAdmin() {
//    return this.userRoleEnum.equals(UserRoleEnum.ADMIN);
//  }

  public Comment(CommentRequestDto requestDto, User user, VolunteerWorkPost volunteerWorkPost) {
    this.contents = requestDto.getContents();
    this.user = user;
    this.volunteerWorkPost = volunteerWorkPost;
  }

  public void updateComment(CommentRequestDto requestDto) {
    this.contents = requestDto.getContents();
  }

}
