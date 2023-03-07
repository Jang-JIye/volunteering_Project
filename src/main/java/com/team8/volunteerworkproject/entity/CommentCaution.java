package com.team8.volunteerworkproject.entity;

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


  public CommentCaution(String cautionUserId, Long commentId, String cautionReason) {
    this.cautionUserId = cautionUserId;
    this.commentId = commentId;
    this.cautionReason = cautionReason;
  }
}
