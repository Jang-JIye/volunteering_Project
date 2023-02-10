package com.team8.volunteerworkproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Entity
@Getter
@Builder
@AllArgsConstructor
public class VolunteerWorkPostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postLikeId;

    @Column(nullable = false)
    private boolean isCheck;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)






}
