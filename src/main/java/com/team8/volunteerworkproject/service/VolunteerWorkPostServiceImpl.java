package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.dto.response.AllVolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.dto.response.VolunteerWorkPostResponseDto;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class VolunteerWorkPostServiceImpl implements VolunteerWorkPostService {

  private final VolunteerWorkPostRepository volunteerWorkPostRepository;
  private final VolunteerWorkPostLikeServiceImpl volunteerWorkPostLikeService;
  public static final String CLOUD_FRONT_DOMAIN_NAME = "d261u93iebql1x.cloudfront.net/";

  //게시글 작성
  @Override
  @Transactional
  public VolunteerWorkPostResponseDto createPost(String userId,
      VolunteerWorkPostRequestDto requestDto, String imgPath) {

    VolunteerWorkPost post = new VolunteerWorkPost(userId, requestDto.getTitle(),
        requestDto.getContent(),
        requestDto.getArea(), requestDto.getCenterName(), requestDto.getEndTime(),
        requestDto.getMaxEnrollmentNum(), CLOUD_FRONT_DOMAIN_NAME + imgPath);//닉네임, 지역,
    volunteerWorkPostRepository.save(post);

    return new VolunteerWorkPostResponseDto(post);
  }

  //게시글 수정
  @Override
  @Transactional
  public VolunteerWorkPostResponseDto updatePost(VolunteerWorkPostRequestDto requestDto,
      Long postId, String userId, String imgPath) {
    VolunteerWorkPost post = volunteerWorkPostRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("해당 봉사모집글이 존재하지 않습니다."));
    if (!post.getUserId().equals(userId)) {
      throw new IllegalArgumentException("봉사모집글의 작성자가 일치하지 않습니다.");
    } else {
      post.update(requestDto, CLOUD_FRONT_DOMAIN_NAME + imgPath);
    }
    return new VolunteerWorkPostResponseDto(post);
  }

  @Override
  public String getPostImage(String userId, Long postId) {
    VolunteerWorkPost post = volunteerWorkPostRepository.findByPostIdAndUserId(postId, userId)
        .orElseThrow(
            () -> new IllegalArgumentException("해당 봉사모집글이 존재하지 않거나, 해당기업의 봉사모집글이 아닙니다. "));
     return post.getImage().substring(30);
  }


  //게시글 삭제
  @Override
  public void deletePost(Long postId, String userId) {
    VolunteerWorkPost post = volunteerWorkPostRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("해당 봉사모집글이 존재하지 않습니다."));

    if (!post.getUserId().equals(userId)) {
      throw new IllegalArgumentException("봉사모집글의 작성자가 일치하지 않습니다.");
    } else {
      volunteerWorkPostRepository.delete(post);
    }
  }


  // 전체 모집글 조회
  @Override
  @Transactional(readOnly = true)
  public List<AllVolunteerWorkPostResponseDto> getAllPost() {
    List<VolunteerWorkPost> allVolunteerWorkPost = volunteerWorkPostRepository.findAllByOrderByCreatedAtDesc();
    List<AllVolunteerWorkPostResponseDto> responseDto = new ArrayList<>();
    for (VolunteerWorkPost volunteerWorkPost : allVolunteerWorkPost) {
      responseDto.add(new AllVolunteerWorkPostResponseDto(volunteerWorkPost));
    }
    return responseDto;
  }

  // 선택 모집글 조회
  @Override
  @Transactional(readOnly = true)
  public VolunteerWorkPostResponseDto getPost(Long postId) {
    VolunteerWorkPost post = volunteerWorkPostRepository.findByPostId(postId).orElseThrow(
        () -> new IllegalArgumentException("찾으시는 봉사모집글이 없습니다.")
    );
    int likeNum = volunteerWorkPostLikeService.count(postId);
    VolunteerWorkPostResponseDto responseDto = new VolunteerWorkPostResponseDto(post, likeNum);

    return responseDto;
  }

}
