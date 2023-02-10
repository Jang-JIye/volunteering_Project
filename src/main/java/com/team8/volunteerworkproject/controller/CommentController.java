package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.dto.request.CommentRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentResponseDto;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

  private final CommentServiceImpl commentService;

  // #17-2 댓글 작성
  @PostMapping("/volunteerWorkPosts/{postsId}/comments")
  public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id,
      @RequestBody CommentRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    CommentResponseDto commentResponseDto = commentService.createComment(id, requestDto,
        userDetails.getUserId());
    return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
  }

  // #17-2 댓글 수정
  @PatchMapping("/volunteerWorkPosts/{postsId}/comments/{commentId}")
  public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,
      @RequestBody CommentRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
    CommentResponseDto commentResponseDto = commentService.updateComment(id, requestDto,
        userDetails.getUserId(), commentId);
    return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
  }

  // 17-3 댓글 삭제
  @DeleteMapping("/volunteerWorkPosts/{postsId}/comments/{commentId}")
  public ResponseEntity deleteComment(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
    return commentService.deleteComment(id, userDetails.getUserId(), commentId);
  }
}
