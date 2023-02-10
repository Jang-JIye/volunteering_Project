package com.team8.volunteerworkproject.entity;


import com.team8.volunteerworkproject.dto.request.VolunteerWorkPostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class VolunteerWorkPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String postStatus;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String interestArea;

//    @Column(nullable = false)
//    private LocalDateTime schedule;

    @JoinColumn
    @ManyToOne
    private User user;

    public VolunteerWorkPost(String title, String contents) {
        this.title = getTitle();
        this.contents = getContents();
    }

    public VolunteerWorkPost(VolunteerWorkPostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.interestArea = requestDto.getPostStatus();
    }

    public void update(String title, String contents, String postStatus) {
        this.title = getTitle();
        this.contents = getContents();
        this.postStatus = getPostStatus();
    }
}
