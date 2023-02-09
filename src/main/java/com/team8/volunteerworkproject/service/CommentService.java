package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.CommentRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentResponseDto;
import com.team8.volunteerworkproject.entity.User;
import org.springframework.http.ResponseEntity;

public interface CommentService {

  CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, User user);

  CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, User user,
      Long comment_id);

  ResponseEntity deleteComment();
}
