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
import org.apache.logging.log4j.util.Strings;
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
  private static String imgPath = "challenge/challenge-basic.jpg";

  // 챌린지 등록
  @PostMapping("/admin/challenges")
  public ResponseEntity<StatusResponseDto> createChallenge(
      @RequestPart("requestDto") ChallengeRequestDto requestDto,
      @RequestPart(value = "file", required = false)
      MultipartFile file) throws IOException {
    if (file == null) {
      imgPath = imgPath;
    } else {
      imgPath = s3Service.updateImage(file, dirName);
    }
    ChallengeResponseDto data = challengeService.createChallenge(requestDto, imgPath);
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "챌린지 등록이 완료되었습니다.");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);

  }

  //챌린지 수정
  @PatchMapping("/admin/challenges/{challengeId}")
  public ResponseEntity<StatusAndDataResponseDto> updateChallenge(@PathVariable Long challengeId,
      @RequestPart("requestDto") ChallengeRequestDto requestDto,
      @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
    if (file == null) {
      imgPath = challengeService.getChallengeImage(challengeId);
    } else {
      imgPath = s3Service.updateImage(file, dirName);
    }
    ChallengeResponseDto data = challengeService.updateChallenge(challengeId, requestDto, imgPath);
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "챌린지 수정이 완료되었습니다.", data);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  //챌린지 삭제
  @DeleteMapping("/admin/challenges/{challengeId}")
  public ResponseEntity<StatusResponseDto> deleteChallenge(@PathVariable Long challengeId) {
    challengeService.deleteChallenge(challengeId);
    StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "챌린지 삭제가 완료되었습니다.");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }

  //챌린지 전체 조회
  @GetMapping("/challenges")
  public ResponseEntity<StatusAndDataResponseDto> getAllChallenge() {
    List<AllChallengeResponseDto> data = challengeService.getAllChallenge();

    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "전체 챌린지를 조회했습니다.", data);

    HttpHeaders headers = new HttpHeaders();//필추
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));//필추
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }


  //챌린지 선택 조회
  @GetMapping("/challenges/{challengeId}")
  public ResponseEntity<StatusAndDataResponseDto> getChallege(@PathVariable Long challengeId) {
    ChallengeResponseDto data = challengeService.getChallenge(challengeId);
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK,
        "선택 챌린지 조회가 완료되었습니다.", data);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);

  }

}
