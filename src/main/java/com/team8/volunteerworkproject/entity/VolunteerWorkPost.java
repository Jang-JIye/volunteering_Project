package com.team8.volunteerworkproject.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class VolunteerWorkPost extends Timestamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String postStatus;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String interestArea;

//    @Column(nullable = false)
//    private LocalDateTime schedule;

    @JoinColumn
    @ManyToOne
    private User user;

}
