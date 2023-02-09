package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import lombok.Getter;



@Getter
public class VolunteerWorkPostResponseDto {

    private Long postId;
    private String title;
    private String writer;
    private String local;
    private String status;
//    private LocalDateTime schedule;



    public VolunteerWorkPostResponseDto(VolunteerWorkPost post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.writer = post.getUser().getWriter();
        this.local = post.getInterestArea();
        this.status = post.getPostStatus();
//        this.schedule = post.getSchedule();

    }
}
