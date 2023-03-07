package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.EnrollmentRequestDto;
import com.team8.volunteerworkproject.dto.response.EnrollmentResponseDto;
import com.team8.volunteerworkproject.entity.Enrollment;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.enums.EnrollmentStatus;
import com.team8.volunteerworkproject.repository.EnrollmentRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

  private final EnrollmentRepository enrollmentRepository;
  private final VolunteerWorkPostRepository volunteerWorkPostRepository;

    //참여 신청
  @Override
  public EnrollmentResponseDto attend(Long postId, EnrollmentRequestDto requestDto, String userId) {
    VolunteerWorkPost post = volunteerWorkPostRepository.findByPostId(postId).orElseThrow(
        () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
/*        if (!enrollment.getUserId().equals(userId)) {
            throw new IllegalArgumentException("이미 참여신청한 모집글입니다.");
        } else {
            enrollment.save(userId);
        }
        if (enrollmentRepository.findById(enrollmentId).isPresent()) {
            throw new IllegalArgumentException("이미 참여를 신청한 모집글입니다.");
        }*/
    Enrollment enrollment = new Enrollment(postId, requestDto, userId, post);
    enrollmentRepository.save(enrollment);

    return new EnrollmentResponseDto(enrollment);
  }

  //참여 신청 취소
  @Override
  public void cancel(Long postId, String userId, Long enrollmentId) {
    VolunteerWorkPost Post = volunteerWorkPostRepository.findByPostId(postId).orElseThrow(
        () -> new IllegalArgumentException("모집글이 존재하지 않습니다.")
    );
 /*       if (!enrollment.getUserId().equals(userId)) {
            throw new IllegalArgumentException("참여한 모집글이 아닙니다.");
        }
        enrollment.delete(userId);*/
    Enrollment enrollment = enrollmentRepository.findByEnrollmentId(enrollmentId).orElseThrow(
        () -> new IllegalArgumentException("참가 신청한 게시글이 아닙니다,")
    );
    //본인의 참여 신청이 아닌 경우
    if (!enrollment.getUserId().equals(userId)) {
      throw new IllegalArgumentException("게시글 참여신청자와 일치하지 않습니다.");
    }
    //참여 취소 통과
    enrollmentRepository.delete(enrollment);
  }

  // 나의 참여 봉사 내역 전체 보기
  @Override
  public List<EnrollmentResponseDto> getAllMyEnrollments(String userId) {

    List<Enrollment> allMyEnrollments = enrollmentRepository.findAllByUserIdAndEnrollmentStatusOrderByCreatedAtDesc(userId,
        EnrollmentStatus.COMPLETE);
    List<EnrollmentResponseDto> responseDto = new ArrayList<>();
    for (Enrollment enrollment : allMyEnrollments) {
      responseDto.add(new EnrollmentResponseDto(enrollment));
    }
    return responseDto;
  }

  //게시글 별 참여 신청 내역 조회
  @Override
  public List<EnrollmentResponseDto> getEnrollmentList() {
    List<Enrollment> postEnrollments = enrollmentRepository.findAllByOrderByCreatedAtDesc();
    List<EnrollmentResponseDto> responseDto = new ArrayList<>();
    for (Enrollment enrollment : postEnrollments) {
      responseDto.add (new EnrollmentResponseDto(enrollment));
    }
    return responseDto;
  }
}
