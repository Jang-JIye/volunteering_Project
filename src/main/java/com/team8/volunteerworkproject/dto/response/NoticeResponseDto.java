package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.Notice;
import com.team8.volunteerworkproject.entity.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeResponseDto  extends  Timestamp{



    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private Long noticeId;




    public NoticeResponseDto(Notice notice) {
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createdAt = notice.getCreatedAt();
        this.modifiedAt =notice.getModifiedAt();
        this.noticeId = notice.getNoticeId();


    }



}