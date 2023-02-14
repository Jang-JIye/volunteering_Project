package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.CommentCautionRequestDto;
import com.team8.volunteerworkproject.dto.request.CommentRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentCautionResponseDto;
import com.team8.volunteerworkproject.dto.response.CommentResponseDto;
import com.team8.volunteerworkproject.entity.Comment;
import com.team8.volunteerworkproject.entity.CommentCaution;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.repository.CommentCautionRepository;
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
  private final CommentCautionRepository commentCautionRepository;

  // #17-1 댓글 작성
  @Transactional
  public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto,
      UserDetailsImpl userDetails) {

    VolunteerWorkPost volunteerWorkPost = volunteerWorkPostRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("해당 게시글이 없습니다.")
    );
    //콘텐츠 아읻
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

    if (!userDetails.getUser().isValidId(comment.getUserId())) {
      throw new IllegalArgumentException("본인의 댓글만 수정 가능합니다.");
    }

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
    if (!userDetails.getUser().isValidId(comment.getUserId())) {
      throw new IllegalArgumentException("본인의 댓글만 삭제 가능합니다.");
    }

    commentRepository.delete(comment);
    return new ResponseEntity<>("삭제 완료!", HttpStatus.OK);

  }

  // #18 댓글 신고
  // 1. 게시글에 달린 댓글을 신고하기
  @Transactional
  public CommentCautionResponseDto cautionComment(Long postId, Long commentId,
      CommentCautionRequestDto requestDto,
      UserDetailsImpl userDetails) {
    // 2. 게시글이 존재하지는 확인
    VolunteerWorkPost volunteerWorkPost = volunteerWorkPostRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("해당 게시글이 없습니다.")
    );
    // 3. 댓글 존재여부 판단
    Comment comment = commentRepository.findById(commentId).orElseThrow(
        () -> new IllegalArgumentException("신고할 댓글이 없습니다.")
    );
    // 4. 신고한 댓글을 CommentCaution 에 저장.
    CommentCaution commentCaution = new CommentCaution(postId, commentId, requestDto, userDetails);
    commentCautionRepository.save(commentCaution);
    return new CommentCautionResponseDto(commentCaution);
    // 5. cautionId, commentId, postId, cautionReason 저장

  }

}
