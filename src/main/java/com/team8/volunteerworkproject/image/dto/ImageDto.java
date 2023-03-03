//package com.team8.volunteerworkproject.image.dto;
//
//import com.team8.volunteerworkproject.image.entity.ImageEntity;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//public class ImageDto {
//  private Long id;
//  private String filePath;
//  private String imgFullPath;
//
//  public ImageEntity toEntity(){
//    ImageEntity build = ImageEntity.builder()
//        .id(id)
//        .filePath(filePath)
//        .build();
//    return build;
//  }
//
//  @Builder
//  public ImageDto(Long id, String filePath, String imgFullPath) {
//    this.id = id;
//    this.filePath = filePath;
//    this.imgFullPath = imgFullPath;
//  }
//}