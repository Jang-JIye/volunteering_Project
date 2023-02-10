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
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PostStatus postStatus = PostStatus.TRUE;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String area;

//    @Column(nullable = false)
//    private LocalDateTime schedule;

    @JoinColumn
    @ManyToOne
    private User user;

    public VolunteerWorkPost(String title, String contents) {
        this.title = getTitle();
        this.contents = getContents();
    }

    public VolunteerWorkPost(VolunteerWorkPostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.interestArea = requestDto.getPostStatus();
    }

    public void update(String title, String contents, String postStatus) {
        this.title = getTitle();
        this.contents = getContents();
        this.postStatus = getPostStatus();
    }
}
