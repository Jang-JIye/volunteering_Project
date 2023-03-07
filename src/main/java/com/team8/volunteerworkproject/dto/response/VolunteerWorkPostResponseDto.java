package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import com.team8.volunteerworkproject.enums.PostStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class VolunteerWorkPostResponseDto {

    private Long postId;
    private String title;
    private String content;
    private String area;
    private String status;
    private int likeNum;
    private String centerName; //세부 주소
    private LocalDateTime endTime;
    private int maxEnrollmentNum;
    private PostStatus postStatus;
//    private LocalDateTime schedule;
    private String image;

    public VolunteerWorkPostResponseDto(VolunteerWorkPost post, int likeNum) {

        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.area = post.getArea();
        this.likeNum = likeNum;
        this.centerName = post.getCenterName();
        this.endTime = post.getEndTime();
        this.maxEnrollmentNum = post.getMaxEnrollmentNum();
        this.postStatus = post.getPostStatus();
        this.image = post.getImage();

  }

    public VolunteerWorkPostResponseDto(VolunteerWorkPost post){
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.area = post.getArea();
        this.centerName = post.getCenterName();
        this.endTime = post.getEndTime();

//        this.schedule = post.getSchedule();

    }
}
