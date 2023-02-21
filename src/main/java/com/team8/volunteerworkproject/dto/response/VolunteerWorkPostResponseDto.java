package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.VolunteerWorkPost;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor //테스트 코드작성할 때 필요해서 추가
public class VolunteerWorkPostResponseDto {

    private Long postId;
    private String title;
    private String content;
    private String area;

    private String centerName;
    //  private String status;
   // private int likeNum;
//    private LocalDateTime schedule;

    public VolunteerWorkPostResponseDto(VolunteerWorkPost post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.area = post.getArea();
        this.centerName = post.getCenterName();
        //        this.likeNum = likeNum;

//        this.schedule = post.getSchedule();

  }
}
