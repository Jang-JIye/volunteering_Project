package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;
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

    //게시글 작성
    @Override
    public VolunteerWorkPostResponseDto createPost(VolunteerWorkPostRequestDto requestDto, String userId) {
        //User user = (User) userRepository.findByUserId(userDetails.getUserId()).orElseThrow(() -> new IllegalArgumentException("동일한 유저가 아님"));

        VolunteerWorkPost post = new VolunteerWorkPost(userId, requestDto);//닉네임, 지역,
        volunteerWorkPostRepository.save(post);

        System.out.println("게시글이 등록되었습니다.");
        return new VolunteerWorkPostResponseDto(post);
    }

    //게시글 수정
    @Override
    public VolunteerWorkPostResponseDto updatePost(VolunteerWorkPostRequestDto requestDto, Long postId, String userId) {
        VolunteerWorkPost post = volunteerWorkPostRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (!post.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("게시글의 작성자가 아닙니다.");
        } else {
            post.update(requestDto);
            volunteerWorkPostRepository.save(post); // update 로 변경된 나머지를 다시 DB에 저장
        }
        System.out.println("게시글이 수정되었습니다.");
        return new VolunteerWorkPostResponseDto(post);
    }

    //게시글 삭제
    @Override
    public VolunteerWorkPostResponseDto deletePost(Long postId, String userId) {
        VolunteerWorkPost post = volunteerWorkPostRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (!post.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("게시글의 작성자가 아닙니다.");
        } else {
            volunteerWorkPostRepository.delete(post);
        }
        System.out.println("게시글이 삭제되었습니다.");
        return new VolunteerWorkPostResponseDto(post);
    }
//----------------------------------------------------------------------------------------------------------------------

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
