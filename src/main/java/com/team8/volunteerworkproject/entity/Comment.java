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
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Column(nullable = false)
  private String content;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private VolunteerWorkPost volunteerWorkPost;

  public boolean isWriter(Long userId) {
    return this.user.isValidUserId(userId) || this.user.isAdmin();
  }

  public Comment(CommentRequestDto requestDto, User user, VolunteerWorkPost volunteerWorkPost) {
    this.content = requestDto.getContent();
    this.user = user;
    this.volunteerWorkPost = volunteerWorkPost;
  }

  public void updateComment(CommentRequestDto requestDto) {
    this.content = requestDto.getContent();
  }

}
