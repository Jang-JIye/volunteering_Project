package com.team8.volunteerworkproject.image.controller;

import com.team8.volunteerworkproject.image.dto.GalleryDto;
import com.team8.volunteerworkproject.image.service.GalleryService;
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
public class GalleryController {
  private S3Service s3Service;
  private GalleryService galleryService;

  @GetMapping("/gallery")
  public String dispWrite(Model model) {
    List<GalleryDto> galleryDtoList = galleryService.getList();

    model.addAttribute("galleryList", galleryDtoList);

    return "/gallery";
  }

  @PostMapping("/gallery")
  public String execWrite(GalleryDto galleryDto, MultipartFile file) throws IOException {
    String imgPath = s3Service.upload(file);
    galleryDto.setFilePath(imgPath);



    galleryService.savePost(galleryDto);

    return "redirect:/gallery";
  }
}