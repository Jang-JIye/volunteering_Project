package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.entity.User;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.enums.PostStatus;
import com.team8.volunteerworkproject.enums.StatusEnum;
import com.team8.volunteerworkproject.repository.UserRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class) //junit 과 mockito 를 연결해주는 어노테이션
class VolunteerWorkPostServiceImplTest {

    @Mock // Mock 객체를 만들어서 반환.
    VolunteerWorkPostRepository volunteerWorkPostRepository;

    @Mock
    UserRepository userRepository;
/*    @Mock
    private JwtUtil jwtUtil;*/

    @InjectMocks // @Mock 또는 @Spy 로 생성된 가짜 객체를 자동으로 주입시켜주는 어노테이션
    VolunteerWorkPostServiceImpl volunteerWorkPostService;

/*
    @Spy //테스트 코드 내에서 메소드를 그대로 사용하겠다는 어노테이션.
    private JwtUtil jwtUtil;
    -> @Spy 를 사용했을 때는 오류가 남.  @Mock 으로 내부 구현이 없는 상태로 만들고 작동하니 테스트에 성공함.
    ->대체로 Mock 을 사용하기르 권장하지만 외부라이브러리를 사용한 테스트에는 @Spy 를 사용하는 것을 추천한다.
*/



    @Test
    @DisplayName("게시글 작성")
    void createPost() {
        //given(가짜 값)
        VolunteerWorkPostRequestDto requestDto = VolunteerWorkPostRequestDto.builder()
                .title("안녕하세요")
                .content("내용1")
                .postStatus(PostStatus.TRUE)// 모집 확인
                .area("제주")
                .build();
        UserDetailsImpl userDetails = new UserDetailsImpl(new User());
        //when
        volunteerWorkPostService.createPost(userDetails.getUserId(), requestDto);

        //then
        verify(volunteerWorkPostRepository).save(any(VolunteerWorkPost.class));
                /*assertThat(requestAuthResponseDto.getType()).isEqualTo();
        assertThat(requestAuthResponseDto.getType()).isEqualTo();
        assertThat(requestAuthResponseDto.getType()).isEqualTo();*/
    }

    @Test
    @DisplayName(("게시글 수정"))
    void updatePost() {
        //given
        VolunteerWorkPostRequestDto requestDto = VolunteerWorkPostRequestDto.builder().title("hello")
                .content("내용2")
                .postStatus(PostStatus.TRUE)
                .area("서울")
                .build();//하나 더....

        // UserDetailsImpl userDetails = new UserDetailsImpl(new User());
        User user = new User();
        VolunteerWorkPost post = new VolunteerWorkPost();

        when(volunteerWorkPostRepository.findByPostId(anyLong())).thenReturn(Optional.of(post));// ->오류

        //when
        volunteerWorkPostService.updatePost(requestDto, post.getPostId(), post.getUserId());

        //then
        verify(volunteerWorkPostRepository, times(1)).save(any(VolunteerWorkPost.class));
        //volunteerWorkPostRepository.save(new VolunteerWorkPost());
//
//        //given
//        VolunteerWorkPost post = mock(VolunteerWorkPost.class);
//        User user = mock(User.class);
//        VolunteerWorkPostRequestDto requestDto = mock(VolunteerWorkPostRequestDto.class);
//        VolunteerWorkPostResponseDto responseDto = mock(VolunteerWorkPostResponseDto.class);
//
//        String userId = "1";
//        when(volunteerWorkPostRepository.findById(any(Long.class))).thenReturn(any());
//        when(post.getUserId().equals(userId)).thenReturn(false);

        //when
        //then
    }

/*    @Test
    @DisplayName("게시글 수정_게시글 id가 없을 때 예외 발생")
    void updatePost_invalid_post() {
        //given
        *//*when(volunteerWorkPostRepository.findByPostId(anyLong())).thenReturn(Optional.of(new VolunteerWorkPost()));*//*
        when(volunteerWorkPostRepository.findByPostId(anyLong())).thenReturn(Optional.empty());

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            volunteerWorkPostService.updatePost(a)
        });//이류를 모르ㅡ겠다.*/



/*
    @Test
    @DisplayName("게시글 삭제")
    void deletePost() {
        //given
        VolunteerWorkPostRequestDto requestDto = VolunteerWorkPostRequestDto.builder()
                .title("안녕하세요")
                .contents("내용1")
                .postStatus(StatusEnum.OK)// 모집 확인
                .area("제주")
                .build();
        UserDetailsImpl userDetails = new UserDetailsImpl(new User());
        VolunteerWorkPost post = new VolunteerWorkPost(userDetails.getUserId(), requestDto);

        VolunteerWorkPost p = volunteerWorkPostRepository.findById(anyLong()).

                //when

                VolunteerWorkPost post = volunteerWorkPostRepository.findById(anyLong()).

                // volunteerWorkPostService.deletePost(anyLong(), new UserDetailsImpl(new User()));
                // volunteerWorkPostService.deletePost(requestDto.getPostId(), new UserDetailsImpl(new User()));

                //then
                //delete
                        Assertions.assertEquals(0, volunteerWorkPostRepository.count());

    }*/
}