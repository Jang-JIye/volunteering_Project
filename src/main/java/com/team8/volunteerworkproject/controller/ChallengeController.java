package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.dto.request.ChallengeRequestDto;
import com.team8.volunteerworkproject.dto.response.ChallengeResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.service.ChallengeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeServiceImpl challengeService;

    // 챌린지 등록
    @PostMapping("/challenges")
    public ResponseEntity<StatusAndDataResponseDto> createChallenge(@RequestBody ChallengeRequestDto requestDto){
        ChallengeResponseDto data = challengeService.createChallenge(requestDto);
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "챌린지 등록이 완료되었습니다.", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto,headers, HttpStatus.OK);

    }

    //챌린지 수정
    @PatchMapping("/challenges/{challengeId}")
    public ResponseEntity<StatusAndDataResponseDto> updateChallenge(@PathVariable Long challengeId, @RequestBody ChallengeRequestDto requestDto) {
        ChallengeResponseDto data = challengeService.updateChallenge(challengeId, requestDto);
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "챌린지 수정이 완료되었습니다.", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto,headers,HttpStatus.OK);
    }

    //챌린지 삭제
    @DeleteMapping("/challenges/{challengeId}")
    public ResponseEntity<StatusResponseDto> deleteChallenge(@PathVariable Long challengeId){
        challengeService.deleteChallenge(challengeId);
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK,"챌린지 삭제가 완료되었습니다.");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto,headers,HttpStatus.OK);
    }

}
