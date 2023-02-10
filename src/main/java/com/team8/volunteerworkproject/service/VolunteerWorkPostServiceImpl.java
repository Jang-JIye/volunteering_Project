package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.entity.User;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.repository.UserRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerWorkPostServiceImpl implements VolunteerWorkPostService{

    private final VolunteerWorkPostRepository volunteerWorkPostRepository;
    private final UserRepository userRepository;

    //게시글 등록
    @Transactional
    public VolunteerWorkPostResponseDto createPost(VolunteerWorkPostRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = (User) userRepository.findByUserId(userDetails.getUserId()).orElseThrow(() -> new IllegalArgumentException("동일한 유저가 아님"));

        VolunteerWorkPost post = new VolunteerWorkPost(requestDto.getTitle(), requestDto.getContents());//닉네임, 지역,
        volunteerWorkPostRepository.save(post);

        System.out.println("게시글이 등록되었습니다.");
        return new VolunteerWorkPostResponseDto(post);
    }

    //게시물 수정
    @Transactional
    public VolunteerWorkPostResponseDto updatePost(VolunteerWorkPostRequestDto requestDto, Long postId, UserDetailsImpl userDetails) {
        VolunteerWorkPost post = volunteerWorkPostRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("포스트를 찾을 수 없습니다."));

        if (!post.getUser().getUserId().equals(userDetails.getUserId())) {
            throw new IllegalArgumentException("게시글의 작성자가 아닙니다");
        }

        post.update(requestDto.getTitle(), requestDto.getContents(), requestDto.getPostStatus());//지역
        volunteerWorkPostRepository.save(post); // update 로 변경된 나머지를 다시 DB에 저장

        System.out.println("게시글이 수정되었습니다.");
        return new VolunteerWorkPostResponseDto(post);
    }

    //게시물 삭제
    @Transactional
    public VolunteerWorkPostResponseDto deletePost(Long postId, UserDetailsImpl userDetails) {
        VolunteerWorkPost post = volunteerWorkPostRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 업습니다"));

        if (!post.getUser().getUserId().equals(userDetails.getUserId())) {
            throw new IllegalArgumentException("게시글의 작성자가 아닙니다");
        }

        volunteerWorkPostRepository.delete(post);
        System.out.println("게시글이 삭제되었습니다. ");
        return new VolunteerWorkPostResponseDto(post);
    }


    @Override
    @Transactional(readOnly = true)
    public List<AllVolunteerWorkPostResponseDto> getAllPost(){
        List<VolunteerWorkPost> allVolunteerWorkPost = volunteerWorkPostRepository.findAllByOrderByCreatedAtDesc();
        List<AllVolunteerWorkPostResponseDto> responseDto = new ArrayList<>();
        for (VolunteerWorkPost volunteerWorkPost : allVolunteerWorkPost){
            responseDto.add(new AllVolunteerWorkPostResponseDto(volunteerWorkPost));
        }
        return responseDto;
    }


    @Override
    @Transactional(readOnly = true)
    public VolunteerWorkPostResponseDto getPost(Long postId) {
        VolunteerWorkPost post = volunteerWorkPostRepository.findByPostId(postId).orElseThrow(
                () -> new IllegalArgumentException("찾으시는 모집글이 없습니다.")
        );
        VolunteerWorkPostResponseDto responseDto = new VolunteerWorkPostResponseDto(post);
        return responseDto;
    }
}
