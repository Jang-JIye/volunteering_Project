//package com.team8.volunteerworkproject.image.service;
//
//import com.team8.volunteerworkproject.image.dto.ImageDto;
//import com.team8.volunteerworkproject.image.entity.ImageEntity;
//import com.team8.volunteerworkproject.image.repository.ImageRepository;
//import java.util.ArrayList;
//import java.util.List;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class ImageService {
//  private ImageRepository imageRepository;
//  private final S3Service s3Service;
//
//  public void savePost(ImageDto imageDto) {
//    imageRepository.save(imageDto.toEntity());
//  }
//
//  public List<ImageDto> getList() {
//    List<ImageEntity> imageEntityList = imageRepository.findAll();
//    List<ImageDto> imageDtoList = new ArrayList<>();
//
//    for (ImageEntity imageEntity : imageEntityList) {
//      imageDtoList.add(convertEntityToDto(imageEntity));
//    }
//
//    return imageDtoList;
//  }
//
//  private ImageDto convertEntityToDto(ImageEntity imageEntity) {
//    return ImageDto.builder()
//        .id(imageEntity.getId())
//        .filePath(imageEntity.getFilePath())
//        .imgFullPath("https://" + s3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + imageEntity.getFilePath())
//        .build();
//  }
//}