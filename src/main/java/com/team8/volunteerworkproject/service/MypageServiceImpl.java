package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.ProfileRequestDto;
import com.team8.volunteerworkproject.dto.response.ProfileResponseDto;
import com.team8.volunteerworkproject.entity.Profile;
import com.team8.volunteerworkproject.repository.MyPageRepository;
import com.team8.volunteerworkproject.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MyPageService{

  private final UserRepository userRepository;
  private final MyPageRepository myPageRepository;

  @Override
  public ProfileResponseDto createProfile(String userId, ProfileRequestDto requestDto) {
    // 입력한 아이디의 회원이 존재하는지 확인
    userRepository.findByUserId(userId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
    );
    // 입력한 아이디의 회원의 프로필이 존재하는지 확인
    Optional<Profile> found = myPageRepository.findByUserId(userId);
    if (found.isPresent()) throw new IllegalArgumentException("프로필이 존재합니다.");
    // Dto 의 image 값이 null 이면 이미지를 제외하고 객체 생성
    Profile profile;
    if (requestDto.getImage() == null) {
      profile = new Profile(userId, requestDto.getNickname(), requestDto.getPhoneNumber(), requestDto.getInterestArea());
    } else {
      profile = new Profile(userId, requestDto.getNickname(), requestDto.getImage(), requestDto.getInterestArea(), requestDto.getPhoneNumber());
    }
    myPageRepository.save(profile);
    return new ProfileResponseDto(userId, profile);
  }
}
