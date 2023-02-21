package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.common.S3Uploader;
import com.team8.volunteerworkproject.dto.request.ProfileRequestDto;
import com.team8.volunteerworkproject.dto.response.ProfileResponseDto;
import com.team8.volunteerworkproject.entity.Profile;
import com.team8.volunteerworkproject.entity.User;
import com.team8.volunteerworkproject.repository.ProfileRepository;
import com.team8.volunteerworkproject.repository.UserRepository;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;
  private final PasswordEncoder passwordEncoder;

  private final S3Uploader s3Uploader;

  @Value("${cloud.aws.s3.bucket}")
  private String bucketName;



//  @Override
//  public ProfileResponseDto createProfile(String userId, ProfileRequestDto requestDto) {
//    // 입력한 아이디의 회원이 존재하는지 확인
//    userRepository.findByUserId(userId).orElseThrow(
//        () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
//    );
//    // 입력한 아이디의 회원의 프로필이 존재하는지 확인
//    Optional<Profile> found = profileRepository.findByUserId(userId);
//    if (found.isPresent()) {
//      throw new IllegalArgumentException("프로필이 존재합니다.");
//    }
//    // Dto 의 image 값이 null 이면 이미지를 제외하고 객체 생성
//    Profile profile;
//    if (requestDto.getImage() == null) {
//      profile = new Profile(userId, requestDto.getNickname(), requestDto.getPhoneNumber(),
//          requestDto.getInterestArea());
//    } else {
//      profile = new Profile(userId, requestDto.getNickname(), requestDto.getImage(),
//          requestDto.getInterestArea(), requestDto.getPhoneNumber());
//    }
//    profileRepository.save(profile);
//    return new ProfileResponseDto(userId, profile);
//  }

  @Override
  public ProfileResponseDto getCustomerProfile(String userId) {
    Profile profile = profileRepository.findByUserId(userId).orElseThrow(
        () -> new IllegalArgumentException("프로필을 작성해 주세요.")
    );
    return new ProfileResponseDto(userId, profile);

  }

  @Override
  public ProfileResponseDto updateProfile(String userId, MultipartFile multipartFile, ProfileRequestDto requestDto) {
    User user = userRepository.findByUserId(userId).orElseThrow(
        () -> new IllegalArgumentException("등록된 아이디가 없습니다.")
    );
    Profile profile = profileRepository.findByUserId(userId).orElseThrow(
        () -> new IllegalArgumentException("프로필이 존재하지 않습니다.")
    );
    if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
    if (requestDto.getImage() == null) {
      profile.updateWithoutImage(requestDto);
      user.changeNickname(requestDto.getNickname());
    } else {
      profile.updateWithImage(requestDto);
      user.changeNickname(requestDto.getNickname());
    }
    profileRepository.save(profile);
    userRepository.save(user);
    return new ProfileResponseDto(userId, profile);
  }
}
