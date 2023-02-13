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

    @Column
    private String nickname;


    public VolunteerWorkPost(String userId, String title, String content, PostStatus postStatus, String area) {
        this.userId = userId;
        this.title = title;
        this.postStatus = postStatus;
        this.content = content;
        this.area = area;
    }


//    @Column(nullable = false)
//    private LocalDateTime schedule;



    public void update(VolunteerWorkPostRequestDto requestDto) {//지역, 상태,
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.postStatus = requestDto.getPostStatus();//상태
        this.area = requestDto.getArea();

    }
}
