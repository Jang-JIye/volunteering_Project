package com.team8.volunteerworkproject.image.repository;

import com.team8.volunteerworkproject.image.entity.GalleryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<GalleryEntity, Long> {
  @Override
  List<GalleryEntity> findAll();
}