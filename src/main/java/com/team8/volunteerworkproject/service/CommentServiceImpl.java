package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.CommentRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentResponseDto;
import com.team8.volunteerworkproject.entity.Comment;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.repository.CommentRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final VolunteerWorkPostRepository volunteerWorkPostRepository;

  // #17-1 댓글 작성
  @Transactional
  public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto,
      UserDetailsImpl userDetails) {

    VolunteerWorkPost volunteerWorkPost = volunteerWorkPostRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("해당 게시글이 없습니다.")
    );

    Comment comment = new Comment(requestDto, userDetails.getUserId(), volunteerWorkPost);
    commentRepository.save(comment);
    return new CommentResponseDto(comment);
  }

  // #17-2 댓글 수정
  @Transactional
  public CommentResponseDto updateComment(Long postId, CommentRequestDto requestDto,
      UserDetailsImpl userDetails, Long commentId) {

    volunteerWorkPostRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
    );

    Comment comment = commentRepository.findById(commentId).orElseThrow(
        () -> new IllegalArgumentException("수정할 댓글이 없습니다.")
    );

    comment.updateComment(requestDto);
    return new CommentResponseDto(comment);
  }

  // 17-3 댓글 삭제
  @Transactional
  public ResponseEntity deleteComment(Long postId, UserDetailsImpl userDetails, Long commentId) {

    volunteerWorkPostRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
    );

    Comment comment = commentRepository.findById(commentId).orElseThrow(
        () -> new IllegalArgumentException("삭제할 댓글이 없습니다.")
    );

    commentRepository.delete(comment);
    return new ResponseEntity<>("삭제 완료!", HttpStatus.OK);

  }
}
