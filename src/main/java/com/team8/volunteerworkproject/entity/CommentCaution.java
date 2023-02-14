package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.CommentCautionRequestDto;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class CommentCaution extends Timestamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cautionId;

  @Column(nullable = false)
  private String cautionReason;

  private Long commentId;


  private UserDetailsImpl userDetails;

  public CommentCaution(Long commentId, CommentCautionRequestDto requestDto,
      UserDetailsImpl userDetails) {
    this.cautionReason = requestDto.getCautionReason();
    this.commentId = commentId;
  }

  public CommentCaution(Long postId, Long commentId, CommentCautionRequestDto requestDto,
      UserDetailsImpl userDetails) {
    super();
  }
}
