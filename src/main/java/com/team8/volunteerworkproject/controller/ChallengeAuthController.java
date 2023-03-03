package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.dto.request.ChallengeAuthRequestDto;
import com.team8.volunteerworkproject.dto.response.*;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.ChallengeAuthServiceImpl;
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
public class ChallengeAuthController {

    private final ChallengeAuthServiceImpl challengeAuthService;

    //챌린지 인증(자랑) 등록하기
    @PostMapping("/challenge-auth")
    public ResponseEntity<StatusResponseDto> createChallenge(@RequestBody ChallengeAuthRequestDto requestDto){
        ChallengeAuthResponseDto data = challengeAuthService.createChallengeAuth(requestDto);
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "챌린지 인증하셨습니다.");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto,headers, HttpStatus.OK);
    }

    //챌린지 인증(자랑) 전체 조회
    @GetMapping("/challenge-auth")
    public ResponseEntity<StatusAndDataResponseDto> getAllChallenge(){
        List<AllChallengeAuthResponseDto> data = challengeAuthService.getAllChallengeAuth();

        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "전체 챌린지 자랑하기를 조회했습니다.", data);

        HttpHeaders headers = new HttpHeaders();//필추
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));//필추
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    //챌린지 인증(자랑) 선택 조회
    @GetMapping("/challenge-auth/{challengeAuthId}")
    public ResponseEntity<StatusAndDataResponseDto> getChallege(@PathVariable Long challengeAuthId){
        ChallengeAuthResponseDto data = challengeAuthService.getCahllengeAuth(challengeAuthId);
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "선택 챌린지 자랑 조회가 완료되었습니다.", data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);

    }

    //챌린지 인증(자랑) 삭제
    @DeleteMapping("/challenge-auth/{challengeAuthId}")
    public ResponseEntity<StatusResponseDto> deleteChallengeAuth(@PathVariable Long challengeAuthId){challengeAuthService.deleteChallengeAuth(challengeAuthId);
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK,"챌린지 자랑 삭제가 완료되었습니다.");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto,headers,HttpStatus.OK);
    }

    //챌린지 인증(자랑) 수정
    @PatchMapping("/{challengeAuthId}")
    public ResponseEntity<StatusAndDataResponseDto> updateChallengeAuth(@PathVariable Long challengeAuthId, @RequestBody ChallengeAuthRequestDto requestDto) {
        ChallengeAuthResponseDto data = challengeAuthService.updateChallengeAuth(challengeAuthId, requestDto);
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "챌린지 인증 수정이 완료되었습니다.", data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto,headers,HttpStatus.OK);
    }


    //나의 챌린지 전체조회
    @GetMapping("/mypage/myChallengeAuthList")
    public ResponseEntity<StatusAndDataResponseDto> getAllChallenge(@AuthenticationPrincipal UserDetailsImpl userDetails){
        List<AllChallengeAuthResponseDto> data = challengeAuthService.getAllChallengeMyAuth(userDetails.getUserId());

        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "나의 챌린지를 조회했습니다.", data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }


    
}
