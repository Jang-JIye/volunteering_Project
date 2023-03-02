package com.team8.volunteerworkproject.image.dto;

import com.team8.volunteerworkproject.image.entity.GalleryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {
  private Long id;
  private String title;
  private String filePath;
  private String imgFullPath;

  public GalleryEntity toEntity(){
    GalleryEntity build = GalleryEntity.builder()
        .id(id)
        .title(title)
        .filePath(filePath)
        .build();
    return build;
  }

  @Builder
  public GalleryDto(Long id, String title, String filePath, String imgFullPath) {
    this.id = id;
    this.title = title;
    this.filePath = filePath;
    this.imgFullPath = imgFullPath;
  }
}