package com.team8.volunteerworkproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class VolunteerWorkPostLike extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postLikeId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String userId;


    public VolunteerWorkPostLike(Long postId, String userId) {
        this.postId = postId;
        this.userId = userId;
    }


}
