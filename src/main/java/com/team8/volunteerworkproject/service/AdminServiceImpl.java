package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.NoticeRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentCautionResponseDto;
import com.team8.volunteerworkproject.dto.response.NoticeResponseDto;
import com.team8.volunteerworkproject.entity.Comment;
import com.team8.volunteerworkproject.entity.CommentCaution;
import com.team8.volunteerworkproject.entity.Notice;
import com.team8.volunteerworkproject.entity.Profile;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.enums.UserStatus;
import com.team8.volunteerworkproject.repository.CommentCautionRepository;
import com.team8.volunteerworkproject.repository.CommentRepository;
import com.team8.volunteerworkproject.repository.NoticeRepository;
import com.team8.volunteerworkproject.repository.ProfileRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

  private final NoticeRepository noticeRepository;

  private final VolunteerWorkPostRepository volunteerWorkPostRepository;

  private final CommentRepository commentRepository;

  private final ProfileRepository profileRepository;

  private final CommentCautionRepository commentCautionRepository;

  //공지사항 작성
  @Override
  @Transactional
  public NoticeResponseDto createNotice(NoticeRequestDto requestDto) {
    Notice notice = new Notice(requestDto.getTitle(), requestDto.getContent());
    noticeRepository.save(notice);
    return new NoticeResponseDto(notice);

  }

  //공지사항 전체 조회
  @Override
  @Transactional
  public List<NoticeResponseDto> getNoticeList() {

    List<Notice> notices = noticeRepository.findAllByOrderByModifiedAtDesc();
    List<NoticeResponseDto> noticeResponseDtolist = new ArrayList<>();

    for (Notice notice : notices) {
      noticeResponseDtolist.add(new NoticeResponseDto(notice));
    }
    return noticeResponseDtolist;
  }

  //공지사항 선택 조회
  @Override
  @Transactional
  public NoticeResponseDto findNotice(Long noticeId) {
    Notice notice = noticeRepository.findById(noticeId).orElseThrow(
        () -> new IllegalArgumentException("조회하려는 공지사항이 없습니다.")
    );
    return new NoticeResponseDto(notice);
  }


  //공지사항 수정
  @Override
  @Transactional
  public NoticeResponseDto updateNotice(Long noticeId, NoticeRequestDto requestDto) {
    Notice notice = noticeRepository.findById(noticeId)
        .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다."));

    notice.update(requestDto.getTitle(), requestDto.getContent());

    noticeRepository.save(notice);

    return new NoticeResponseDto(notice);
  }


  //공지사항 삭제
  @Override
  public void deleteNotice(Long noticeId) {
    Notice notice = noticeRepository.findById(noticeId).orElseThrow(
        () -> new IllegalArgumentException("삭제하고자 하는 공지사항이 없습니다.")
    );
    noticeRepository.delete(notice);

  }

  //게시글 삭제
  @Override
  public void adminDeletePost(Long postId) {
    VolunteerWorkPost post = volunteerWorkPostRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

    volunteerWorkPostRepository.delete(post);

  }

  //댓글 삭제
  @Override
  public void adminDeleteComment(Long postId, Long commentId) {
    volunteerWorkPostRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

    Comment comment = commentRepository.findById(commentId).orElseThrow(
        () -> new IllegalArgumentException("삭제 할 댓글이 없습니다.")
    );

    commentRepository.delete(comment);

  }

  //유저 활동 정지
  @Override
  public void userBlock(String userId) {
    Profile profile = profileRepository.findByUserId(userId).orElseThrow(
        () -> new IllegalArgumentException("프로필이 존재하지 않습니다.")
    );
    profile.changeUserEnum(UserStatus.BLOCK);
    profileRepository.save(profile);
  }

  //유저 활동 재개
  @Override
  public void userNormal(String userId) {
    Profile profile = profileRepository.findByUserId(userId).orElseThrow(
        () -> new IllegalArgumentException("프로필이 존재하지 않습니다.")
    );
    profile.changeUserEnum(UserStatus.NORMAL);
    profileRepository.save(profile);
  }

  //신고 유저 조회
  @Override
  @Transactional
  public List<CommentCautionResponseDto> getCautionUserList() {

    List<CommentCaution> CommentCautions = commentCautionRepository.findAllByOrderByModifiedAtDesc();
    List<CommentCautionResponseDto> CommentCautionResponseDtoList = new ArrayList<>();

    for (CommentCaution commentCaution : CommentCautions) {
      CommentCautionResponseDtoList.add(new CommentCautionResponseDto(commentCaution));
    }
    return CommentCautionResponseDtoList;
  }

}




