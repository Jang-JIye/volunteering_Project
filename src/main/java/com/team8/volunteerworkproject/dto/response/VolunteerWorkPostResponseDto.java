package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor //테스트 코드작성할 때 필요해서 추가
public class VolunteerWorkPostResponseDto {

    private Long postId;
    private String title;
    private String content;
    private String area;
    private String status;
    private int likeNum;
    private String centerName; //세부 주소
    private String date;//모집 날짜
//    private LocalDateTime schedule;

    public VolunteerWorkPostResponseDto(VolunteerWorkPost post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.area = post.getArea();
        this.likeNum = likeNum;
        this.centerName = post.getCenterName();
        this.date = post.getDate();

//        this.schedule = post.getSchedule();

  }
}
