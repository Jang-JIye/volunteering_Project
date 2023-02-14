package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.SigninRequestDto;
import com.team8.volunteerworkproject.dto.request.SignupRequestDto;
import com.team8.volunteerworkproject.entity.Profile;
import com.team8.volunteerworkproject.entity.User;
import com.team8.volunteerworkproject.enums.UserRoleEnum;
import com.team8.volunteerworkproject.jwt.AuthenticatedUserInfoDto;
import com.team8.volunteerworkproject.repository.ProfileRepository;
import com.team8.volunteerworkproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ProfileService profileService;
  private final PasswordEncoder passwordEncoder;

  private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
  private final ProfileRepository profileRepository;

  @Override
  public void signup(SignupRequestDto requestDto) {
    String userId = requestDto.getUserId();
    String password = passwordEncoder.encode(requestDto.getPassword());
    String nickname = requestDto.getNickname();
    String companyRegisterNumb = requestDto.getCompanyRegisterNumb();

    Optional<User> found = userRepository.findByUserId(requestDto.getUserId());
    if (found.isPresent()) {
      throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
    }
    UserRoleEnum role = UserRoleEnum.USER;

    if (requestDto.getAdminToken() != null) {
      if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
        throw new IllegalArgumentException("관리자 암호가 일치하지 않아 등록할 수 없습니다.");
      }
      role = UserRoleEnum.ADMIN;
    }
    if (requestDto.getCompanyRegisterNumb() != null) {
      Optional<User> found2 = userRepository.findByCompanyRegisterNumb(
          requestDto.getCompanyRegisterNumb());
      if (found2.isPresent()) {
        throw new IllegalArgumentException("중복된 사업자등록번호가 존재합니다.");
      }
      role = UserRoleEnum.COMPANY;
    }

    User user = new User(userId, password, nickname, role, companyRegisterNumb);
    Profile profile = new Profile(userId, nickname);
    userRepository.save(user);
    profileRepository.save(profile);
  }

  @Override
  public AuthenticatedUserInfoDto signin(SigninRequestDto requestDto) {
    User user = userRepository.findByUserId(requestDto.getUserId()).orElseThrow(
        () -> new IllegalArgumentException("등록된 아이디가 없습니다.")
    );
    if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
    return new AuthenticatedUserInfoDto(user.getRole(), user.getUserId());
  }

  @Override
  public void signout(HttpServletRequest request) {
  }

  
}
