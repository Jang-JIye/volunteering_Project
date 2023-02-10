package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

  private Long id;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public CommentResponseDto(Comment comment) {
    this.id = comment.getCommentId();
    this.content = comment.getContent();
    // createdAT, modifiedAt 삭제?
    this.createdAt = comment.getCreatedAt();
    this.modifiedAt = comment.getModifiedAt();

  }

}
