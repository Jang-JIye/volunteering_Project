package com.team8.volunteerworkproject.service;

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
    public List<VolunteerWorkPostResponseDto> getAllPost() {
        List<VolunteerWorkPost> post = volunteerWorkPostRepository.findAllByCreatedAtDesc();
        List<VolunteerWorkPostResponseDto> responseDtos = new ArrayList<>();
        for(VolunteerWorkPost volunteerWorkPost : post){
            responseDtos.add(new VolunteerWorkPostResponseDto(volunteerWorkPost));
        }
        return responseDtos;
    }

    @Override
    public VolunteerWorkPostResponseDto getPost(Long postId) {
        VolunteerWorkPost post = volunteerWorkPostRepository.findById(postId).orElseThrow(
                () -> new RuntimeException("찾으시는 판매자가 없습니다. ")
        );
        return new VolunteerWorkPostResponseDto(post);
    }
}
