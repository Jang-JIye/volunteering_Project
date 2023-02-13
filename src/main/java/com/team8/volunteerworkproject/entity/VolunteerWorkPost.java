package com.team8.volunteerworkproject.entity;


import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import com.team8.volunteerworkproject.enums.PostStatus;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
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

    @Column
    private String nickname;

//    @Column(nullable = false)
//    private LocalDateTime schedule;

    @JoinColumn
    @ManyToOne
    private User user;


    public VolunteerWorkPost(String userId, VolunteerWorkPostRequestDto requestDto) {
        this.title = getTitle();
        this.content = getContent();
        this.nickname = getNickname();
        this.postStatus = getPostStatus();
        this.area = getArea();

    }

    public VolunteerWorkPost(String title, String content, PostStatus postStatus, String area) {
        this.title = getTitle();
        this.content = getContent();
        this.postStatus = getPostStatus();
        this.area = getArea();
    }


    public void update(VolunteerWorkPostRequestDto requestDto) {//지역, 상태,
        this.title = getTitle();
        this.content = getContent();
        this.postStatus = getPostStatus();//상태
        this.area = getArea();

    }
}
