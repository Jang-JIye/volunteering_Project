package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.CommentCautionRequestDto;
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
  private String cautionUserId;

  @Column(nullable = false)
  private String cautionReason;

  @Column(nullable = false)
  private Long commentId;


  public CommentCaution(String cautionUserId, Long commentId, CommentCautionRequestDto requestDto) {
    this.cautionUserId = cautionUserId;
    this.commentId = commentId;
    this.cautionReason = requestDto.getCautionReason();
  }
}
