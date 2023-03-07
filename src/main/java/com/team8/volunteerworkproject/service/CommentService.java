package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.CommentRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentCautionResponseDto;
import com.team8.volunteerworkproject.dto.response.CommentResponseDto;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface CommentService {

  // #17-1 댓글 작성
  CommentResponseDto createComment(Long postId, CommentRequestDto requestDto,
      UserDetailsImpl userDetails);

  // #17-2 댓글 수정
  CommentResponseDto updateComment(Long postId, CommentRequestDto requestDto,
      UserDetailsImpl userDetails, Long commentId);

  // #17-3 댓글 삭제
  ResponseEntity deleteComment(Long postId, UserDetailsImpl userDetails, Long commentId);

  // #18 댓글 신고
  CommentCautionResponseDto cautionComment(Long postId, Long commentId,
      String cautionReason);

  //해당 게시글 댓글 조회
  @Transactional
  List<CommentResponseDto> getCommentList(Long postId);

//  // #댓글 조회
//  @Transactional
//  List<CommentResponseDto> getCommentList();
}

