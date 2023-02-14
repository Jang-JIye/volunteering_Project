package com.team8.volunteerworkproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String userId;

    public Application(Long postId, String userId) {
        this.postId = postId;
        this.userId = userId;

    }
}
