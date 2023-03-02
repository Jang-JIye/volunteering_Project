package com.team8.volunteerworkproject.image.controller;

import com.team8.volunteerworkproject.image.dto.ImageDto;
import com.team8.volunteerworkproject.image.service.ImageService;
import com.team8.volunteerworkproject.image.service.S3Service;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class ImageController {
  private S3Service s3Service;
  private ImageService imageService;

  @GetMapping("/images")
  public String dispWrite(Model model) {
    List<ImageDto> imageDtoList = imageService.getList();

    model.addAttribute("imageList", imageDtoList);

    return "/images";
  }

  @PostMapping("/images")
  public String execWrite(ImageDto imageDto, MultipartFile file) throws IOException {
    String imgPath = s3Service.upload(file);
    imageDto.setFilePath(imgPath);

    imageService.savePost(imageDto);

    return "redirect:/images";
  }
}