package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

  private Long commentId;
  private Long postId;
  private String contents;
  private String nickname;

  public CommentResponseDto(Comment comment) {
    this.commentId = comment.getCommentId();
    this.postId = comment.getVolunteerWorkPost().getPostId();
    this.contents = comment.getContents();
    this.nickname = comment.getUserNickname();
  }
}
