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
        List<VolunteerWorkPost> allVolunteerWorkPost = volunteerWorkPostRepository.findAllByCreatedAtDesc();
        List<AllVolunteerWorkPostResponseDto> responseDtos = new ArrayList<>();
        for (VolunteerWorkPost volunteerWorkPost : allVolunteerWorkPost){
            responseDtos.add(new AllVolunteerWorkPostResponseDto(volunteerWorkPost));
        }
        return responseDtos;
    }


    @Override
    public VolunteerWorkPostResponseDto getPost(Long postId) {
        VolunteerWorkPost post = volunteerWorkPostRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("찾으시는 모집글이 없습니다.")
        );
        return new VolunteerWorkPostResponseDto(post);
    }
}
