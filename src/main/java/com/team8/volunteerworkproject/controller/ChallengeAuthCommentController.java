package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.dto.request.ChallengeAuthCommentRequestDto;
import com.team8.volunteerworkproject.dto.response.ChallengeAuthCommentResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.ChallengeAuthCommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge-auth")
public class ChallengeAuthCommentController {

    private final ChallengeAuthCommentServiceImpl challengeAuthCommentService;

    //댓글 작성
    @PostMapping("/{challengeAuthId}/challengeAuthComments")
    public ResponseEntity<StatusResponseDto> createAuthComment(@PathVariable Long challengeAuthId, @RequestBody ChallengeAuthCommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ChallengeAuthCommentResponseDto commentResponseDto = challengeAuthCommentService.createAuthComment(challengeAuthId,requestDto, userDetails);
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "댓글을 작성하였습니다.");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    //댓글 수정
    @PatchMapping("/{challengeAuthId}/challengeAuthComment/{challengeAuthCommentId}")
    public ResponseEntity<ChallengeAuthCommentResponseDto> updateChallengeAuthComment(@PathVariable Long challengeAuthId, @RequestBody ChallengeAuthCommentRequestDto requestDto, @PathVariable Long challengeAuthCommentId,  @AuthenticationPrincipal UserDetailsImpl userDetails) {

        ChallengeAuthCommentResponseDto challengeAuthCommentResponseDto = challengeAuthCommentService.updateChallengeAuthComment(challengeAuthId, requestDto, challengeAuthCommentId, userDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return ResponseEntity.status(HttpStatus.OK).body(challengeAuthCommentResponseDto);
    }

    //댓글 삭제
    @DeleteMapping("/{challengeAuthId}/challengeAuthComments/{challengeAuthCommentsId}")
    public ResponseEntity<StatusResponseDto> deleteAuthComment(@PathVariable Long challengeAuthId, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long challengeAuthCommentsId) {
        challengeAuthCommentService.deleteAuthComment(challengeAuthId,userDetails,challengeAuthCommentsId);
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK,"댓글이 삭제되었습니다.");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto,headers,HttpStatus.OK);}


}
