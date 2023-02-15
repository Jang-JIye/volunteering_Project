package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.dto.request.CommentCautionRequestDto;
import com.team8.volunteerworkproject.dto.request.CommentRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentCautionResponseDto;
import com.team8.volunteerworkproject.dto.response.CommentResponseDto;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.CommentServiceImpl;
import java.nio.charset.Charset;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

  // #17-1 댓글 작성
  @PostMapping("/volunteerWorkPosts/{postId}/comments")
  public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long postId,
      @RequestBody CommentRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    CommentResponseDto commentResponseDto = commentService.createComment(postId, requestDto,
        userDetails);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
  }

  // #17-2 댓글 수정
  @PatchMapping("/volunteerWorkPosts/{postId}/comments/{commentId}")
  public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long postId,
      @RequestBody CommentRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {

    CommentResponseDto commentResponseDto = commentService.updateComment(postId, requestDto,
        userDetails, commentId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
  }

  // 17-3 댓글 삭제
  @DeleteMapping("/volunteerWorkPosts/{postId}/comments/{commentId}")
  public ResponseEntity deleteComment(@PathVariable Long postId,
      @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return commentService.deleteComment(postId, userDetails, commentId);
  }

  // #18 댓글 신고
  @PostMapping("/volunteerWorkPosts/{postId}/comments/{commentId}/cautions")
  public ResponseEntity<CommentCautionResponseDto> cautionComment(@PathVariable Long postId,
      @PathVariable Long commentId, @RequestBody CommentCautionRequestDto requestDto) {
    CommentCautionResponseDto responseDto = new CommentCautionResponseDto(postId, commentId,
        requestDto);
    commentService.cautionComment(postId, commentId, requestDto);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }


}
