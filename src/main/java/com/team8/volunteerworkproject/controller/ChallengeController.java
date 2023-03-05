package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.dto.request.ChallengeRequestDto;
import com.team8.volunteerworkproject.dto.response.AllChallengeResponseDto;
import com.team8.volunteerworkproject.dto.response.ChallengeResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import com.team8.volunteerworkproject.dto.response.StatusResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.service.ChallengeServiceImpl;
import com.team8.volunteerworkproject.service.S3Service;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeServiceImpl challengeService;
    private final S3Service s3Service;
    private static String dirName = "challenge";

    // 챌린지 등록
    @PostMapping("/admin/challenges")
    public ResponseEntity<ChallengeResponseDto> createChallenge(@RequestBody ChallengeRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(challengeService.createChallenge(requestDto));

    }

    //챌린지 수정
    @PatchMapping("/admin/challenges/{challengeId}")

    public ResponseEntity<ChallengeResponseDto> updateChallenge(@PathVariable Long challengeId, @RequestBody ChallengeRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(challengeService.updateChallenge(challengeId,requestDto));


    //챌린지 삭제
    @DeleteMapping("/admin/challenges/{challengeId}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long challengeId){
        return ResponseEntity.status(HttpStatus.OK).body(challengeService.deleteChallenge(challengeId));
    }

    //챌린지 전체 조회
    @GetMapping("/challenges")
    public ResponseEntity<List<AllChallengeResponseDto>> getAllChallenge(){
        return ResponseEntity.status(HttpStatus.OK).body(challengeService.getAllChallenge());
    }

    //챌린지 선택 조회
    @GetMapping("/challenges/{challengeId}")
    public ResponseEntity<ChallengeResponseDto> getChallege(@PathVariable Long challengeId){
        return ResponseEntity.status(HttpStatus.OK).body(challengeService.getChallenge(challengeId));
    }

}
