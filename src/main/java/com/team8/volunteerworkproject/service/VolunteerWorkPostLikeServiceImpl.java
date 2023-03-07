package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.entity.VolunteerWorkPostLike;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostLikeRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerWorkPostLikeServiceImpl implements VolunteerWorkPostLikeService {

    private final VolunteerWorkPostLikeRepository volunteerWorkPostLikeRepository;
    private final VolunteerWorkPostRepository volunteerWorkPostRepository;

    @Override
    @Transactional
    public void update(Long postId, String userId) {
        VolunteerWorkPost volunteerWorkPost = volunteerWorkPostRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("모집글이 존재하지 않습니다.")
        );
        if(volunteerWorkPostLikeRepository.findByPostIdAndUserId(postId, userId).isPresent()){
            volunteerWorkPostLikeRepository.deleteByPostIdAndUserId(postId, userId);
        }else {
            volunteerWorkPostLikeRepository.save(new VolunteerWorkPostLike(postId,userId));
        }
    }

    //좋아요 카운트
    @Override
    public int count(Long postId) {
        List<VolunteerWorkPostLike> postLikes = volunteerWorkPostLikeRepository.findAllByPostId(postId);
        return postLikes.size();
    }
}
