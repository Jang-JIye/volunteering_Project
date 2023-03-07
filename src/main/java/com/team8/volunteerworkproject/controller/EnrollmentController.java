package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.dto.request.EnrollmentRequestDto;
import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.EnrollmentResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import java.util.List;

import com.team8.volunteerworkproject.entity.Enrollment;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import com.team8.volunteerworkproject.dto.response.StatusResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.EnrollmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;




@RequiredArgsConstructor
@RestController
public class EnrollmentController {
    private final EnrollmentServiceImpl enrollmentService;

    //참여 신청(TRUE(확정) or FALSE(대기))
    @PostMapping("/volunteerWorkPosts/{postId}/enrollments") //TRUE, FALSE
    public ResponseEntity<StatusAndDataResponseDto> attend(@PathVariable Long postId,
                                                           @Valid @RequestBody EnrollmentRequestDto requestDto,
                                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        EnrollmentResponseDto data = enrollmentService.attend(postId, requestDto, userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,"참여신청이 완료되었습니다.", data);

        HttpHeaders headers = new HttpHeaders();//필추
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));//필추

        return new  ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    //참여 취소
    @DeleteMapping("/volunteerWorkPosts/{postId}/enrollments/{enrollmentId}")
    public ResponseEntity<StatusResponseDto> cancel(@PathVariable Long postId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                    @PathVariable Long enrollmentId
    ) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "참여신청을 취소하였습니다.");
        enrollmentService.cancel(postId, userDetails.getUserId(), enrollmentId);

        HttpHeaders headers = new HttpHeaders();//필추
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));//필추

        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @GetMapping("/mypage/enrollments")
    public ResponseEntity<StatusAndDataResponseDto> getAllMyEnrollment(@AuthenticationPrincipal UserDetailsImpl userDetails){
        List<EnrollmentResponseDto> data = enrollmentService.getAllMyEnrollments(userDetails.getUserId());

        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "나의 봉사활동 참여 목록 조회가 완료되었습니다.", data);

        HttpHeaders headers = new HttpHeaders();//필추
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));//필추
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    //봉사 게시글 별 참여 신청 내역
    @GetMapping("/volunteerWorkPosts/{postId}/enrollments")
    public ResponseEntity<StatusAndDataResponseDto> getEnrollmentList(@PathVariable Long postId) {
        List <EnrollmentResponseDto> data = enrollmentService.getEnrollmentList(postId);

        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "참여신청 조회가 완료되었습니다.", data);

        HttpHeaders headers = new HttpHeaders();//필추
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));//필추

        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
}
