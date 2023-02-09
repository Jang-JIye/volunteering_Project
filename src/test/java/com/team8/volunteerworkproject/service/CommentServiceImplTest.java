package com.team8.volunteerworkproject.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.team8.volunteerworkproject.dto.request.CommentRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentResponseDto;
import com.team8.volunteerworkproject.entity.Comment;
import com.team8.volunteerworkproject.entity.User;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.jwt.JwtUtil;
import com.team8.volunteerworkproject.repository.CommentRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

  @Mock
  CommentRepository commentRepository;

  @Mock
  VolunteerWorkPostRepository volunteerWorkPostRepository;

  @Mock
  JwtUtil jwtUtil;

  @InjectMocks
  CommentServiceImpl commentService;

  @Test
  @DisplayName("코멘트 생성 성공")
  void createComment() {
    //given
    Long id = 1L;
    CommentRequestDto commentRequestDto = mock(CommentRequestDto.class);
    User user = mock(User.class);
    VolunteerWorkPost volunteerWorkPost = mock(VolunteerWorkPost.class);
    Comment comment = mock(Comment.class);
    when(volunteerWorkPostRepository.findById(id)).thenReturn(ArgumentMatchers.any());
    CommentResponseDto commentResponseDto = mock(CommentResponseDto.class);
    //when
    CommentResponseDto commentServiceComment = commentService.createComment(id, commentRequestDto,
        user);
    //then
    verify(commentRepository, times(1)).save(any());
  }
}