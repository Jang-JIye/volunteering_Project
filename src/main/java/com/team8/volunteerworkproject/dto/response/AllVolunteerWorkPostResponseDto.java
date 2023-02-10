package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import lombok.Getter;

@Getter
public class AllVolunteerWorkPostResponseDto {

    private Long postId;
    private String title;
    private String userId;
    private String interestArea;
    private String status;

    public AllVolunteerWorkPostResponseDto(VolunteerWorkPost volunteerWorkPost) {
        this.postId = volunteerWorkPost.getId();
        this.title = volunteerWorkPost.getTitle();
        this.userId = volunteerWorkPost.getUser().getUserId();
        this.interestArea = volunteerWorkPost.getInterestArea();
        this.status = volunteerWorkPost.getPostStatus();
//        this.schedule = volunteerWorkPost.getSchedule();
    }

}
