package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.dto.request.NoticeRequestDto;
import com.team8.volunteerworkproject.dto.response.CommentCautionResponseDto;
import com.team8.volunteerworkproject.dto.response.NoticeResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.service.AdminService;
import java.nio.charset.Charset;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

  private final AdminService adminService;

  //공지사항 작성

  @PostMapping("/admin/notices")
  public ResponseEntity<StatusAndDataResponseDto> createNotice(
      @RequestBody NoticeRequestDto requestDto) {
    NoticeResponseDto data = adminService.createNotice(requestDto);
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "(admin) 공지사항 작성이 완료되었습니다.", data);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);

  }

  //공지사항 조회
  @GetMapping("/notices")
  public ResponseEntity<StatusAndDataResponseDto> getNoticeList() {
    List<NoticeResponseDto> data = adminService.getNoticeList();
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "(admin) 공지사항 조회가 완료되었습니다.", data);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    ResponseEntity<List<StatusAndDataResponseDto>> ResponseEntity;
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  //공지사항 선택 조회
  @GetMapping("/notices/{noticeId}")
  public ResponseEntity<StatusAndDataResponseDto> findNotice(@PathVariable Long noticeId) {
    NoticeResponseDto data = adminService.findNotice(noticeId);
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "(admin) 공지사항 선택 조회가 완료되었습니다.", data);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  //공지사항 수정
  @PatchMapping("/admin/notices/{noticeId}")
  public ResponseEntity<StatusAndDataResponseDto> updateNotice(@PathVariable Long noticeId,
      @RequestBody NoticeRequestDto requestDto) {
    NoticeResponseDto data = adminService.updateNotice(noticeId, requestDto);
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "(admin) 공지사항 수정이 완료되었습니다.", data);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  //공지사항 삭제
  @DeleteMapping("/admin/notices/{noticeId}")
  public ResponseEntity<StatusResponseDto> deleteNotice(@PathVariable Long noticeId) {
    adminService.deleteNotice(noticeId);
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK,
        "(admin) 공지사항 삭제가 완료되었습니다.");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  //게시글 삭제
  @DeleteMapping("/admin/volunteerWorkPosts/{postId}")
  public ResponseEntity<StatusResponseDto> adminDeletePost(@PathVariable Long postId) {
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK,
        "(admin) 해당 게시글이 삭제되었습니다.");
    adminService.adminDeletePost(postId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);//responseDto
  }

  //댓글 삭제
  @DeleteMapping("admin/volunteerWorkPosts/{postId}/comments/{commentId}")
  public ResponseEntity<StatusResponseDto> adminDeleteComment(@PathVariable Long postId,
      @PathVariable Long commentId) {
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "(admin) 해당 댓글이 삭제되었습니다.");
    adminService.adminDeleteComment(postId, commentId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);//responseDto
  }

  //유저 활동 정지
  @PatchMapping("/admin/users/{userId}/block")
  public ResponseEntity<StatusResponseDto> userBlock(@PathVariable String userId) {
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK,
        "(admin) 해당 유저가 활동 정지되었습니다.");
    adminService.userBlock(userId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  //유저 활동 재개
  @PatchMapping("/admin/users/{userId}/normal")
  public ResponseEntity<StatusResponseDto> userNormal(@PathVariable String userId) {
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK,
        "(admin) 해당 유저가 활동 재개되었습니다.");
    adminService.userNormal(userId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  // 신고받은 유저 조회
  @GetMapping("/admin/users/black-list")
  public ResponseEntity<StatusAndDataResponseDto> getCautionUserList(@PathVariable Long postId) {
    List<CommentCautionResponseDto> data = adminService.getCautionUserList();
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "(admin) 신고 받은 유저 조회가 완료되었습니다.", data);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    ResponseEntity<List<StatusAndDataResponseDto>> ResponseEntity;
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

}
