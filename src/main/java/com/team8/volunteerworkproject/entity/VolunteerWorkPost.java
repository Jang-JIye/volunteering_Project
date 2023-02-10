package com.team8.volunteerworkproject.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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


}
