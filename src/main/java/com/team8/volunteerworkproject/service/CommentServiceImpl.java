package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.CommentRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentResponseDto;
import com.team8.volunteerworkproject.entity.Comment;
import com.team8.volunteerworkproject.entity.User;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.repository.CommentRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
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
  public CommentResponseDto createComment(Long id, CommentRequestDto requestDto, User user) {

    VolunteerWorkPost volunteerWorkPost = volunteerWorkPostRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 게시글이 없습니다.")
    );

    Comment comment = new Comment(requestDto, user, volunteerWorkPost);
    commentRepository.save(comment);
    return new CommentResponseDto(comment);
  }


  // #17-2 댓글 수정
  @Transactional
  public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user,
      Long commentId) {
    VolunteerWorkPost volunteerWorkPost = volunteerWorkPostRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
    );
    Comment comment = commentRepository.findById(commentId).orElseThrow(
        () -> new IllegalArgumentException("수정할 댓글이 없습니다.")
    );
    if (comment.isWriter(user.getUserId())) {
      comment.updateComment(requestDto);
      CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
      return commentResponseDto;
    }
    throw new IllegalArgumentException("댓글을 수정할 권한이 없습니다.");

  }

  // 17-3 댓글 삭제
  @Transactional
  public ResponseEntity deleteComment(Long id, User user, Long commentId) {
    VolunteerWorkPost volunteerWorkPost = volunteerWorkPostRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
    );
    Comment comment = commentRepository.findById(commentId).orElseThrow(
        () -> new IllegalArgumentException("삭제할 댓글이 없습니다.")
    );
    if (comment.isWriter(user.getUserId())) {
      commentRepository.delete(comment);
      return new ResponseEntity<>("삭제 완료!", HttpStatus.OK);
    }

    throw new IllegalArgumentException("댓글을 삭제할 권한이 없습니다.");
  }
}
