package com.team8.volunteerworkproject.security;

import com.team8.volunteerworkproject.entity.User;
import com.team8.volunteerworkproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    User user = userRepository.findByUserId(userId).orElseThrow(
        () -> new UsernameNotFoundException("사용자를 찾을 수 없습니다.")
    );
    return new UserDetailsImpl(user);
  }
}
