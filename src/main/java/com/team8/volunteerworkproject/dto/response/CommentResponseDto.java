package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentResponseDto {

  private final Long id;
  private final String contents;
  private final LocalDateTime createdAt;
  private final LocalDateTime modifiedAt;

  public CommentResponseDto(Comment comment) {
    this.id = comment.getCommentId();
    this.contents = comment.getContents();
    // createdAT, modifiedAt 삭제?
    this.createdAt = comment.getCreatedAt();
    this.modifiedAt = comment.getModifiedAt();

  }

}
