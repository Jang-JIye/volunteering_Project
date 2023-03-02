package com.team8.volunteerworkproject.image.service;

import com.team8.volunteerworkproject.image.dto.GalleryDto;
import com.team8.volunteerworkproject.image.entity.GalleryEntity;
import com.team8.volunteerworkproject.image.repository.GalleryRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GalleryService {
  private GalleryRepository galleryRepository;
  private final S3Service s3Service;

  public void savePost(GalleryDto galleryDto) {
    galleryRepository.save(galleryDto.toEntity());
  }

  public List<GalleryDto> getList() {
    List<GalleryEntity> galleryEntityList = galleryRepository.findAll();
    List<GalleryDto> galleryDtoList = new ArrayList<>();

    for (GalleryEntity galleryEntity : galleryEntityList) {
      galleryDtoList.add(convertEntityToDto(galleryEntity));
    }

    return galleryDtoList;
  }

  private GalleryDto convertEntityToDto(GalleryEntity galleryEntity) {
    return GalleryDto.builder()
        .id(galleryEntity.getId())
        .title(galleryEntity.getTitle())
        .filePath(galleryEntity.getFilePath())
        .imgFullPath("https://" + s3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + galleryEntity.getFilePath())
        .build();
  }
}