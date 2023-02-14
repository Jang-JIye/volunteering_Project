package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.NoticeRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

    @Getter
    @NoArgsConstructor
    @Entity
    public class Notice extends Timestamp {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long noticeId;

        @Column(nullable = false)
        private String userId;

        @Column(nullable = false)
        private String title;

        @Column(nullable = false)
        private String content;




        public Notice(NoticeRequestDto requestDto, String userId) {
            this.title = requestDto.getTitle();
            this.content = requestDto.getContent();
            this.userId =userId;
        }



        public void update(String title, String content) {
            this.title =title;
            this.content =content;
        }



    }