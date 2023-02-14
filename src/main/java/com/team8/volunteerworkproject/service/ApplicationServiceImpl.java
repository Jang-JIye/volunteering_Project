package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.repository.ApplicationRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{
    private final ApplicationRepository applicationRepository;
    private final VolunteerWorkPostRepository volunteerWorkPostRepository;

    //참여 신청 & 취소
    @Override
    @Transactional
    public void enrollment(Long postId, String userId) {
        VolunteerWorkPost volunteerWorkPost = volunteerWorkPostRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("모집글이 존재하지 않습니다.")
        );
        if (ApplicationRepository.findByUserId(userId).isPresent()) {

        }
    }
}
