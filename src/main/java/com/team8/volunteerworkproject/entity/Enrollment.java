package com.team8.volunteerworkproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Enrollment extends  Timestamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String userId;

    @Column
    private String username;

    public Enrollment(Long postId, String userId) {
        this.postId = postId;
        this.userId = userId;

    }
    //주석

    public void save(String userId) {
        this.userId = getUserId();
    }

    public void delete(String userId) {
        this.userId = getUserId();
    }
}
