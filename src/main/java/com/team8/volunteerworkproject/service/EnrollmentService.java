package com.team8.volunteerworkproject.service;

public interface EnrollmentService {

/*
    void participation(Long postId, String userId);
*/


    void attend(Long postId, String userId);

    void cancel(Long postId, String userId);
}
