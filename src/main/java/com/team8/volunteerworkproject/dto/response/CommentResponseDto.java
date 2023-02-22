package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

  private Long commentId;
  private Long postId;
  private String contents;
  private String nickname;
  private LocalDateTime modifiedAt;

  public CommentResponseDto(Comment comment) {
    this.commentId = comment.getCommentId();
    this.postId = comment.getPostId();
    this.contents = comment.getContents();
    this.nickname = comment.getUserNickname();
    this.modifiedAt = comment.getModifiedAt();
  }
}
