package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.CommentRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentResponseDto;
import org.springframework.http.ResponseEntity;

public interface CommentService {

  // #17-1 댓글 작성
  CommentResponseDto createComment(Long id, CommentRequestDto requestDto, String userId);

  // #17-2 댓글 수정
  CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, String userId,
      Long commentId);

  // #17-3 댓글 삭제
  ResponseEntity deleteComment(Long id, String userId, Long commentId);

}

