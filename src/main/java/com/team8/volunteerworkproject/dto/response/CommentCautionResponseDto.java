package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.dto.request.CommentCautionRequestDto;
import com.team8.volunteerworkproject.entity.CommentCaution;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCautionResponseDto {

  private Long postId;
  private Long commentId;
  private String cautionReason;
  private String message;

  public CommentCautionResponseDto(
      CommentCaution commentCaution) {
    this.cautionReason = commentCaution.getCautionReason();
    this.commentId = commentCaution.getCommentId();
  }

  public CommentCautionResponseDto(Long postId, Long commentId,
      CommentCautionRequestDto requestDto, String message) {
    this.postId = postId;
    this.commentId = commentId;
    this.cautionReason = requestDto.getCautionReason();
    this.message = message;

  }
}