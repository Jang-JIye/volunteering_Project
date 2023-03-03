package com.team8.volunteerworkproject.image.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.team8.volunteerworkproject.entity.Profile;
import com.team8.volunteerworkproject.repository.ProfileRepository;
import com.team8.volunteerworkproject.service.ProfileService;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3Service {
  private AmazonS3 s3Client;

  @Value("${cloud.aws.credentials.accessKey}")
  private String accessKey;

  @Value("${cloud.aws.credentials.secretKey}")
  private String secretKey;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${cloud.aws.region.static}")
  private String region;

  public static final String CLOUD_FRONT_DOMAIN_NAME = "d261u93iebql1x.cloudfront.net";

  private final ProfileService profileService;
  private final ProfileRepository profileRepository;

  @PostConstruct
  public void setS3Client() {
    AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

    s3Client = AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withRegion(this.region)
        .build();
  }

//  public String upload(MultipartFile file) throws IOException {
//    String fileName = file.getOriginalFilename();
//
//    s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
//        .withCannedAcl(CannedAccessControlList.PublicRead));
//
//    return fileName;
//  }

  @Transactional
  public String updateProfileImage(String userId, MultipartFile file) throws IOException {

    Profile profile = profileRepository.findByUserId(userId).orElseThrow(
        () -> new IllegalArgumentException("프로필이 존재하지 않습니다.")
    );

    SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
    String fileName = "profile/" + date.format(new Date()) + "-" + file.getOriginalFilename();

    profile.updateProfileImage(fileName);

    s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
        .withCannedAcl(CannedAccessControlList.PublicRead));

    return fileName;
  }


}