package com.team8.volunteerworkproject;

import com.team8.volunteerworkproject.entity.Challenge;
import com.team8.volunteerworkproject.entity.ChallengeAuth;
import com.team8.volunteerworkproject.entity.Comment;
import com.team8.volunteerworkproject.entity.Enrollment;
import com.team8.volunteerworkproject.entity.Notice;
import com.team8.volunteerworkproject.entity.Profile;
import com.team8.volunteerworkproject.entity.User;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.enums.PostStatus;
import com.team8.volunteerworkproject.enums.UserRoleEnum;
import com.team8.volunteerworkproject.repository.ChallengeAuthRepository;
import com.team8.volunteerworkproject.repository.ChallengeRepository;
import com.team8.volunteerworkproject.repository.CommentRepository;
import com.team8.volunteerworkproject.repository.EnrollmentRepository;
import com.team8.volunteerworkproject.repository.NoticeRepository;
import com.team8.volunteerworkproject.repository.ProfileRepository;
import com.team8.volunteerworkproject.repository.UserRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
public class VolunteerWorkProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(VolunteerWorkProjectApplication.class, args);
  }

//  @Bean
//  public CommandLineRunner test(UserRepository userRepository,
//                                VolunteerWorkPostRepository volunteerWorkPostRepository,
//                                ProfileRepository profileRepository,
//                                ChallengeRepository challengeRepository,
//                                NoticeRepository noticeRepository,
//                                ChallengeAuthRepository challengeAuthRepository,
//                                CommentRepository commentRepository,
//                                EnrollmentRepository enrollmentRepository,
//                                PasswordEncoder passwordEncoder){
//    return (args) -> {
//      userRepository.save(new User("customer1@naver.com", passwordEncoder.encode("1234?5678"), "장현재", UserRoleEnum.USER, null));
//      userRepository.save(new User("customer2@naver.com", passwordEncoder.encode("1234?5678"), "조용연", UserRoleEnum.USER, null));
//      userRepository.save(new User("customer3@naver.com", passwordEncoder.encode("1234?5678"), "손혜은", UserRoleEnum.USER, null));
//      userRepository.save(new User("customer4@naver.com", passwordEncoder.encode("1234?5678"), "장지예", UserRoleEnum.USER, null));
//      userRepository.save(new User("company1@naver.com", passwordEncoder.encode("1234?5678"), "이재원", UserRoleEnum.COMPANY, "111-11-1111"));
//      userRepository.save(new User("company2@naver.com", passwordEncoder.encode("1234?5678"), "기업2", UserRoleEnum.COMPANY, "222-22-2222"));
//      userRepository.save(new User("company3@naver.com", passwordEncoder.encode("1234?5678"), "기업3", UserRoleEnum.COMPANY, "333-33-3333"));
//      userRepository.save(new User("company4@naver.com", passwordEncoder.encode("1234?5678"), "기업4", UserRoleEnum.COMPANY, "444-44-4444"));
//      userRepository.save(new User("admin@naver.com", passwordEncoder.encode("1234?5678"), "어드민", UserRoleEnum.ADMIN, null));
//
//      profileRepository.save(new Profile("customer1@naver.com", "010-1111-2222", "장현재",  "서울", "기본프로필사진"));
//      profileRepository.save(new Profile("customer2@naver.com", "010-3333-4444", "조용연",  "인천", "기본프로필사진"));
//      profileRepository.save(new Profile("customer3@naver.com", "010-5555-6666", "손혜은",  "부산", "기본프로필사진"));
//      profileRepository.save(new Profile("customer4@naver.com", "010-7777-8888", "장지예",  "부천", "기본프로필사진"));
//      profileRepository.save(new Profile("company1@naver.com", "010-1655-7652", "이재원",  "부천", "기본프로필사진"));
//      profileRepository.save(new Profile("company2@naver.com", "010-4613-1214", "기업2",  "서울", "기본프로필사진"));
//      profileRepository.save(new Profile("company3@naver.com", "010-7115-4611", "기업3",  "대전", "기본프로필사진"));
//      profileRepository.save(new Profile("company4@naver.com", "010-8841-5451", "기업4",  "대구", "기본프로필사진"));
//      profileRepository.save(new Profile("admin@naver.com", "010-4841-8515", "어드민",  "서울", "기본프로필사진"));
//
////      volunteerWorkPostRepository.save(new VolunteerWorkPost("company1@naver.com", "다함께돌봄센터 봉사자 모집", "안녕하세요. 자원봉사자 모집합니다1", "서울", "서울시자원봉사센터 앞", "2023-02-24"));
////      volunteerWorkPostRepository.save(new VolunteerWorkPost("company2@naver.com","환경정화활동 자원봉사자 모집", "안녕하세요. 자원봉사자 모집합니다2", "대전", "대전자원봉사센터 앞", "2023-02-25"));
////      volunteerWorkPostRepository.save(new VolunteerWorkPost("company3@naver.com", "꿈마루 협동조합 발달장애인 돌봄 지원", "안녕하세요. 자원봉사자 모집합니다3", "부산", "부산자원봉사센터 앞", "2023-02-26"));
////      volunteerWorkPostRepository.save(new VolunteerWorkPost("company4@naver.com","서구도서관 자원봉사 모집", "안녕하세요. 자원봉사자 모집합니다4", "인천", "인천자원봉사센터 앞", "2023-02-27"));
////      volunteerWorkPostRepository.save(new VolunteerWorkPost("company1@naver.com", "홀몸 어르신 후원 물품 나눔 봉사", "안녕하세요. 자원봉사자 모집합니다5", "부천", "부천자원봉사센터 앞", "2023-02-28"));
//
//      noticeRepository.save(new Notice("공지사항1", "공지사항입니다1"));
//      noticeRepository.save(new Notice("공지사항2", "공지사항입니다2"));
//      noticeRepository.save(new Notice("공지사항3", "공지사항입니다3"));
//      noticeRepository.save(new Notice("공지사항4", "공지사항입니다4"));
//      noticeRepository.save(new Notice("공지사항5", "공지사항입니다5"));
//      noticeRepository.save(new Notice("공지사항6", "공지사항입니다6"));
//
//      challengeRepository.save(new Challenge("admin@naver.com", "동네 쓰레기 줍기", "쓰레기를 버리지 마세요.", "이미지1"));
//      challengeRepository.save(new Challenge("admin@naver.com", "플로깅 인증하기!", "쓰레기를 주으면서 조깅하기.", "이미지2"));
//      challengeRepository.save(new Challenge("admin@naver.com", "안입는 옷 기부하기", "누군가에게는 소중한 옷", "이미지3"));
//      challengeRepository.save(new Challenge("admin@naver.com", "유니세프 후원하기", "대지진으로 삶의 터전을 잃은 어린이 유니세프와 함께 후원해주세요", "이미지4"));
//      challengeRepository.save(new Challenge("admin@naver.com", "초록우산 후원하기", "아이들에게 밝은 내일을 선물해주세요.", "이미지5"));
//      challengeRepository.save(new Challenge("admin@naver.com", "헌혈하기", "헌혈에 동참해주세요.", "이미지5"));
//
//      commentRepository.save(new Comment("참여하고싶어요~!", "customer1@naver.com", "장현재", 5L));
//      commentRepository.save(new Comment("저도 참여하고싶어요~!", "customer2@naver.com", "조용연", 5L));
//      commentRepository.save(new Comment("우와~~!", "customer3@naver.com", "손혜은", 5L));
//      commentRepository.save(new Comment("좋아요~!", "customer4@naver.com", "장지예", 5L));
//      commentRepository.save(new Comment("어떻게 참여하나요?", "customer1@naver.com", "장현재", 1L));
//      commentRepository.save(new Comment("좋은 게시글이네요~", "customer2@naver.com", "조용연", 1L));
//      commentRepository.save(new Comment("참여하고싶어요~!", "customer1@naver.com", "장현재", 2L));
//      commentRepository.save(new Comment("저도 참여하고싶어요~!", "customer2@naver.com", "조용연", 2L));
//      commentRepository.save(new Comment("우와~~!", "customer3@naver.com", "손혜은", 3L));
//      commentRepository.save(new Comment("좋아요~!", "customer4@naver.com", "장지예", 3L));
//      commentRepository.save(new Comment("어떻게 참여하나요?", "customer1@naver.com", "장현재", 4L));
//      commentRepository.save(new Comment("좋은 게시글이네요~", "customer2@naver.com", "조용연", 4L));
//
//      challengeAuthRepository.save(new ChallengeAuth("customer1@naver.com", "인증합니다.", "오늘 플로깅 했어요~!", "이미"));
//      challengeAuthRepository.save(new ChallengeAuth("customer2@naver.com", "헌혈했어요~", "여러분들도 헌혈하세요~", "이미지2"));
//      challengeAuthRepository.save(new ChallengeAuth("customer3@naver.com", "쓰레기가 너무 많아요 ㅠ", "열심히 주웠습니다ㅎㅎ", "이미지3"));
//      challengeAuthRepository.save(new ChallengeAuth("customer4@naver.com", "후원했어요~", "튀르키예에 더 큰 피해가 없길!", "이미지4"));
//      challengeAuthRepository.save(new ChallengeAuth("customer1@naver.com", "초록우산 후원 인증", "아이들의 미래를 밝히는 후원 캠페인! ", "이미지5"));
//      challengeAuthRepository.save(new ChallengeAuth("customer2@naver.com", "아름다운가게 옷 기부~", "안입는 옷이 많아서 기부했어요^^", "이미지6"));
//      challengeAuthRepository.save(new ChallengeAuth("customer3@naver.com", "헌혈인증", "요즘 헌혈 공급이 적데요~ 많이들 참여하세요ㅠㅠ", "이미지7"));
//      challengeAuthRepository.save(new ChallengeAuth("customer4@naver.com", "바다 쓰레기 줍기", "오늘 아침에 해변에 있는 쓰레기를 주웠어요 엄청 많음 ㅠㅠ", "이미지8"));
//
//
//    };
//  }


}
