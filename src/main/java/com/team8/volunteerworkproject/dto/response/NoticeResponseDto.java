package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.Notice;
import com.team8.volunteerworkproject.entity.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeResponseDto  extends  Timestamp{

    private String userId;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;




    public NoticeResponseDto(String userId, Notice notice) {
        this.userId = userId;
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createdAt = notice.getCreatedAt();
        this.modifiedAt =notice.getModifiedAt();


    }

    public NoticeResponseDto(Notice notice) {
        this.userId = notice.getUserId();
        this.title = notice.getTitle();
        this.content =notice.getContent();
        this.createdAt = notice.getCreatedAt();
        this.modifiedAt =notice.getModifiedAt();
    }

}