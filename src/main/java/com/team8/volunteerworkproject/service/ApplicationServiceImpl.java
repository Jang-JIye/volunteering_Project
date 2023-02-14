package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.entity.Application;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.repository.ApplicationRepository;
import com.team8.volunteerworkproject.repository.UserRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{
    private final ApplicationRepository applicationRepository;
    private final VolunteerWorkPostRepository volunteerWorkPostRepository;
    private final UserRepository userRepository;

    //참여 신청
    @Override
    @Transactional
    public void attend(Long postId, String userId) {
        VolunteerWorkPost volunteerWorkPost = volunteerWorkPostRepository.findByPostId(postId).orElseThrow(
                () -> new IllegalArgumentException("모집글이 존재하지 않습니다.")
        );
        //이미 참여신청을 한 경우 (중복 신청)
        if (applicationRepository.findByPostIdAndUserId(postId, userId).isPresent()) {
            throw new IllegalArgumentException("이미 참여를 신청한 모집글입니다.");
        //참여 신청 통과
        } else {
            applicationRepository.save(new Application(postId, userId));
        }
    }

    //참여 취소
    @Override
    @Transactional
    public void cancel(Long postId, String userId) {
        VolunteerWorkPost volunteerWorkPost = volunteerWorkPostRepository.findByPostId(postId).orElseThrow(
                () -> new IllegalArgumentException("모집글이 존재하지 않습니다.")
        );
        //참여하지 않은 모집글을 취소하는 경우("참여한 모집글이 아닙니다.")
        if (applicationRepository.findByPostIdAndUserId(postId, userId).isEmpty()) {
            throw new IllegalArgumentException("참여한 모집글이 아닙니다.");
            //참여 취소 통과
        } else {
            applicationRepository.delete(a);
        }

    }
}
