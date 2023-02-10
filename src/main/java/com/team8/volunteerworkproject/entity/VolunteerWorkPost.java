package com.team8.volunteerworkproject.entity;


import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.enums.PostStatus;
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

    public VolunteerWorkPost(String title, String content) {
        this.title = getTitle();
        this.content = getContent();
    }

    public void update(String title, String content, String postStatus) {
        this.title = getTitle();
        this.content = getContent();
        this.postStatus = getPostStatus();//지역
    }
}
