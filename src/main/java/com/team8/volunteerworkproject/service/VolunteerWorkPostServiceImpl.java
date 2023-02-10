package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerWorkPostServiceImpl implements VolunteerWorkPostService{

    private final VolunteerWorkPostRepository volunteerWorkPostRepository;
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
