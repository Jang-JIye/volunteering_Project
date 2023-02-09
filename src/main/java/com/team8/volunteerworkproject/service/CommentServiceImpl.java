package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.CommentRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentResponseDto;
import com.team8.volunteerworkproject.entity.Comment;
import com.team8.volunteerworkproject.entity.User;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.repository.CommentRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import lombok.RequiredArgsConstructor;
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
  @Override
  public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, User user) {

    VolunteerWorkPost volunteerWorkPost = volunteerWorkPostRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 게시글이 없습니다.")
    );

    Comment comment = new Comment(commentRequestDto, user, volunteerWorkPost);
    commentRepository.save(comment);
    return new CommentResponseDto(comment);
  }


  // #17-2 댓글 수정
  @Override
  public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, User user,
      Long comment_id) {

  }

  // 17-3 댓글 삭제
  @Transactional
  public ResponseEntity deleteComment() {

  }
}
