package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.dto.request.NoticeRequestDto;
import com.team8.volunteerworkproject.dto.response.NoticeResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.AdminService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    //공지사항 작성
    @PostMapping("/notices")
    public ResponseEntity<StatusAndDataResponseDto> createNotice(@RequestBody NoticeRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        NoticeResponseDto data = adminService.createNotice(userDetails.getUserId(), requestDto);
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "공지사항 작성이 완료되었습니다.", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto,headers,HttpStatus.OK);

//        return  ResponseEntity.status(HttpStatus.CREATED).body(adminService.createNotice(requestDto,userDetails.getUserId()));
    }

    //공지사항 조회
    @GetMapping("/notices")
    public ResponseEntity<StatusAndDataResponseDto> getNoticeList() {
        List<NoticeResponseDto> data = adminService.getNoticeList();
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "공지사항 조회가 완료되었습니다.", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        ResponseEntity<List<StatusAndDataResponseDto>> ResponseEntity;
        return new ResponseEntity<>(responseDto,headers, HttpStatus.OK);
    }
    //공지사항 선택 조회
    @GetMapping("/notices/{noticeId}")
    public ResponseEntity<StatusAndDataResponseDto> findNotice(@PathVariable Long noticeId,@AuthenticationPrincipal UserDetailsImpl userDetails){
     NoticeResponseDto data = adminService.findNotice(noticeId,userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "공지사항 선택 조회가 완료되었습니다.", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto,headers,HttpStatus.OK);
    }

    //공지사항 수정
    @PatchMapping("/notices/{noticeId}")
    public ResponseEntity<StatusAndDataResponseDto> updateNotice(@PathVariable Long noticeId, @RequestBody NoticeRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        NoticeResponseDto data = adminService.updateNotice(noticeId, requestDto, userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "공지사항 수정이 완료되었습니다.", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto,headers,HttpStatus.OK);
    }

    //공지사항 삭제
    @DeleteMapping("/notices/{noticeId}")
    public  ResponseEntity<StatusResponseDto> deleteNotice(@PathVariable Long noticeId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        adminService.deleteNotice(noticeId,userDetails.getUserId());
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK,"공지사항 삭제가 완료되었습니다.");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto,headers,HttpStatus.OK);
    }
}
