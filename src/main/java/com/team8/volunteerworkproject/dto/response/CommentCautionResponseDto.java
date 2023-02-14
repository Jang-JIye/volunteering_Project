package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.CommentCaution;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCautionResponseDto {

  private String cautionReason;
  private Long commentId;

  public CommentCautionResponseDto(CommentCaution commentCaution) {
    this.cautionReason = commentCaution.getCautionReason();
    this.commentId = commentCaution.getCommentId();
  }


}