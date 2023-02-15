package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.dto.request.CommentCautionRequestDto;
import com.team8.volunteerworkproject.entity.CommentCaution;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCautionResponseDto {

  private Long postId;
  private String cautionReason;
  private Long commentId;

  public CommentCautionResponseDto(CommentCaution commentCaution) {
    this.cautionReason = commentCaution.getCautionReason();
    this.commentId = commentCaution.getCommentId();
  }

  public CommentCautionResponseDto(Long postId, Long commentId,
      CommentCautionRequestDto requestDto) {
    this.postId = postId;
    this.commentId = commentId;
    this.cautionReason = requestDto.getCautionReason();
  }
}