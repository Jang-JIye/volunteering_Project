package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.EnrollmentRequestDto;
import com.team8.volunteerworkproject.dto.response.EnrollmentResponseDto;
import com.team8.volunteerworkproject.entity.Enrollment;
import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.repository.EnrollmentRepository;
import com.team8.volunteerworkproject.repository.UserRepository;
import com.team8.volunteerworkproject.repository.VolunteerWorkPostRepository;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final VolunteerWorkPostRepository volunteerWorkPostRepository;
    private final UserRepository userRepository;
    private final Enrollment enrollment;

    //참여 신청
    @Override
    public EnrollmentResponseDto attend(Long postId, EnrollmentRequestDto requestDto, UserDetailsImpl userDetails, Long enrollmentId) {
        VolunteerWorkPost volunteerWorkPost = volunteerWorkPostRepository.findByPostId(postId).orElseThrow(
                () -> new IllegalArgumentException("모집글이 존재하지 않습니다."));
/*        if (!enrollment.getUserId().equals(userId)) {
            throw new IllegalArgumentException("이미 참여신청한 모집글입니다.");
        } else {
            enrollment.save(userId);
        }
        if (enrollmentRepository.findById(enrollmentId).isPresent()) {
            throw new IllegalArgumentException("이미 참여를 신청한 모집글입니다.");
        }*/
        Enrollment enrollment = new Enrollment(postId, requestDto, userDetails.getUserId());
        enrollmentRepository.save(enrollment);

        return new EnrollmentResponseDto(enrollment);
    }

    //참여 신청 취소
    @Override
    public EnrollmentResponseDto cancel(Long postId, UserDetailsImpl userDetails, Long enrollmentId) {
        VolunteerWorkPost Post = volunteerWorkPostRepository.findByPostId(postId).orElseThrow(
                () -> new IllegalArgumentException("모집글이 존재하지 않습니다.")
        );
 /*       if (!enrollment.getUserId().equals(userId)) {
            throw new IllegalArgumentException("참여한 모집글이 아닙니다.");
        }
        enrollment.delete(userId);*/
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(
                () -> new IllegalArgumentException("참가 신청 내역이 없습니다.")
        );
        //참여하지 않은 모집글을 취소하는 경우
        if (enrollmentRepository.findById(enrollmentId).isEmpty()) {
            throw new IllegalArgumentException("참여한 모집글이 아닙니다.");
        }
        //본인의 참여 신청이 아닌 경우
        if (!enrollment.getUserId().equals(userDetails.getUserId())) {
            throw new IllegalArgumentException("취소하는 모집글 참여신청자와 일치하지 않습니다.");
        }
        //참여 취소 통과
        enrollmentRepository.delete(enrollment);
        return null;
    }
}
