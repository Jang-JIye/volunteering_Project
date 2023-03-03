package com.team8.volunteerworkproject.image.controller;

import com.team8.volunteerworkproject.dto.response.StatusAndDataResponseDto;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.image.service.S3Service;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import java.io.IOException;
import java.nio.charset.Charset;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("images")
public class ImageController {
  private final S3Service s3Service;

//  @GetMapping("/images")
//  public String dispWrite(Model model) {
//    List<ImageDto> imageDtoList = imageService.getList();
//
//    model.addAttribute("imageList", imageDtoList);
//
//    return "/images";
//  }

//  @PostMapping("/images")
//  public String execWrite(ImageDto imageDto, MultipartFile file) throws IOException {
//    String imgPath = s3Service.upload(file);
//    imageDto.setFilePath(imgPath);
//
//    imageService.savePost(imageDto);
//
//    return "redirect:/images";
//  }

  @PostMapping("/profileImage")
  public ResponseEntity<StatusAndDataResponseDto> profileImage(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("file") MultipartFile file) throws IOException {
    String imgPath = s3Service.updateProfileImage(userDetails.getUserId(), file);
    StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "프로필 사진이 등록되었습니다.", imgPath);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
    return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
  }
}