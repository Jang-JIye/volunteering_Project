package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import lombok.Getter;



@Getter
public class VolunteerWorkPostResponseDto {
    private Long postId;

    private String userId;
    private String title;
    private String content;
    private String area;
    private String status;
//    private LocalDateTime schedule;


    public VolunteerWorkPostResponseDto(VolunteerWorkPost post) {
        this.postId = post.getPostId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.area = post.getArea();
        this.status = String.valueOf(post.getPostStatus());
//        this.schedule = post.getSchedule();

    }
}
