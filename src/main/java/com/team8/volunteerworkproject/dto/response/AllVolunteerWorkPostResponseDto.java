package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AllVolunteerWorkPostResponseDto {

    private Long postId;
    private String title;
    private String area;
    private String postStatus;

    private Integer likeNum;

    //    private LocalDateTime schedule;

    public AllVolunteerWorkPostResponseDto(VolunteerWorkPost volunteerWorkPost , Integer likeNum) {
        this.postId = volunteerWorkPost.getPostId();
        this.title = volunteerWorkPost.getTitle();
        this.area = volunteerWorkPost.getArea();
        this.postStatus = String.valueOf(volunteerWorkPost.getPostStatus());
        this.likeNum = likeNum;
//        this.schedule = volunteerWorkPost.getSchedule();
    }

}
