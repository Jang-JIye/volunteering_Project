package com.team8.volunteerworkproject;

import com.team8.volunteerworkproject.entity.Profile;
import com.team8.volunteerworkproject.entity.User;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.enums.PostStatus;
import com.team8.volunteerworkproject.enums.UserRoleEnum;
import com.team8.volunteerworkproject.repository.ProfileRepository;
import com.team8.volunteerworkproject.repository.UserRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
@EnableCaching
public class VolunteerWorkProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(VolunteerWorkProjectApplication.class, args);

  }

//  @Bean
//  public CommandLineRunner test(UserRepository userRepository,
//                                VolunteerWorkPostRepository volunteerWorkPostRepository,
//                                ProfileRepository profileRepository,
//                                PasswordEncoder passwordEncoder){
//    return (args) -> {
//      userRepository.save(new User("customer1@naver.com", passwordEncoder.encode("1234?5678"), "고객1", UserRoleEnum.USER, null));
//      userRepository.save(new User("customer2@naver.com", passwordEncoder.encode("1234?5678"), "고객2", UserRoleEnum.USER, null));
//      userRepository.save(new User("customer3@naver.com", passwordEncoder.encode("1234?5678"), "고객3", UserRoleEnum.USER, null));
//      userRepository.save(new User("customer4@naver.com", passwordEncoder.encode("1234?5678"), "고객4", UserRoleEnum.USER, null));
//      userRepository.save(new User("customer5@naver.com", passwordEncoder.encode("1234?5678"), "고객5", UserRoleEnum.USER, null));
//      userRepository.save(new User("customer6@naver.com", passwordEncoder.encode("1234?5678"), "고객6", UserRoleEnum.USER, null));
//      userRepository.save(new User("customer7@naver.com", passwordEncoder.encode("1234?5678"), "고객7", UserRoleEnum.USER, null));
//      userRepository.save(new User("customer8@naver.com", passwordEncoder.encode("1234?5678"), "고객8", UserRoleEnum.USER, null));
//      userRepository.save(new User("admin@naver.com", passwordEncoder.encode("1234?5678"), "어드민", UserRoleEnum.ADMIN, null));
//
//      profileRepository.save(new Profile("customer1@naver.com", "010-1111-2222", "고객1",  "인천", "기본프로필사진"));
//
//      volunteerWorkPostRepository.save(new VolunteerWorkPost("customer1@naver.com", "게시글1", "내용1", PostStatus.TRUE, "인천"));
//      volunteerWorkPostRepository.save(new VolunteerWorkPost("customer1@naver.com", "게시글2", "내용2", PostStatus.TRUE, "서울"));
//      volunteerWorkPostRepository.save(new VolunteerWorkPost("customer1@naver.com", "게시글3", "내용3", PostStatus.TRUE, "부산"));
//      volunteerWorkPostRepository.save(new VolunteerWorkPost("customer1@naver.com", "게시글4", "내용4", PostStatus.TRUE, "제주"));
//      volunteerWorkPostRepository.save(new VolunteerWorkPost("customer1@naver.com", "게시글5", "내용5", PostStatus.TRUE, "부천"));
//      volunteerWorkPostRepository.save(new VolunteerWorkPost("customer1@naver.com", "게시글6", "내용6", PostStatus.TRUE, "미국"));
//    };
//  }

}
