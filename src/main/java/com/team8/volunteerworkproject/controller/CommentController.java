package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.dto.request.CommentCautionRequestDto;
import com.team8.volunteerworkproject.dto.request.CommentRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentCautionResponseDto;
import com.team8.volunteerworkproject.dto.response.CommentResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.CommentServiceImpl;
import java.nio.charset.Charset;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

  private final CommentServiceImpl commentService;

  // #17-1 λκΈ μμ±
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

  // #17-2 λκΈ μμ 
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

  // 17-3 λκΈ μ­μ 
  @DeleteMapping("/volunteerWorkPosts/{postId}/comments/{commentId}")
  public ResponseEntity deleteComment(@PathVariable Long postId,
      @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return commentService.deleteComment(postId, userDetails, commentId);
  }

  // #18 λκΈ μ κ³ 
  @PostMapping("/volunteerWorkPosts/{postId}/comments/{commentId}/cautions")
  public ResponseEntity<CommentCautionResponseDto> cautionComment(@PathVariable Long postId,
      @PathVariable Long commentId, @RequestBody CommentCautionRequestDto requestDto) {
    CommentCautionResponseDto responseDto = new CommentCautionResponseDto(postId, commentId,
        requestDto, "λκΈμ΄ μ κ³ λμμ΅λλ€.");
    commentService.cautionComment(postId, commentId, requestDto.getCautionReason());
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  // κ²μκΈμ λκΈ μ‘°ν
  @GetMapping("/volunteerWorkPosts/{postId}/comments")
  public ResponseEntity<StatusAndDataResponseDto> getCommentList(@PathVariable Long postId) {
    List<CommentResponseDto> data = commentService.getCommentList(postId);
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "λκΈ μ‘°νκ° μλ£λμμ΅λλ€.", data);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }


}
