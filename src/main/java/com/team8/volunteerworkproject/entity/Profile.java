package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.ProfileRequestDto;
import com.team8.volunteerworkproject.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "profile")
@Getter
@NoArgsConstructor
public class Profile extends Timestamp{

  @Id
  @Column(name = "user_id", nullable = false)
  private String userId;
  private String phoneNumber;
  @Column(nullable = false)
  private String nickname;
  private String interestArea;
  private String companyRegisterNumb;
  private String image = "기본 프로필 사진";
  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UserStatus status = UserStatus.NORMAL;

  public Profile(String userId, String phoneNumber, String nickname, String interestArea,
      String image) {
    this.userId = userId;
    this.phoneNumber = phoneNumber;
    this.nickname = nickname;
    this.interestArea = interestArea;
    this.image = image;
  }

  public Profile(String userId, String phoneNumber, String nickname, String interestArea) {
    this.userId = userId;
    this.phoneNumber = phoneNumber;
    this.nickname = nickname;
    this.interestArea = interestArea;
  }

  public Profile(String userId, String nickname) {
    this.userId = userId;
    this.nickname = nickname;
  }

  public void updateWithImage(ProfileRequestDto requestDto) {
    this.nickname = requestDto.getNickname();
    this.phoneNumber = requestDto.getPhoneNumber();
    this.interestArea = requestDto.getInterestArea();
    this.image = requestDto.getImage();
  }

  public void updateWithoutImage(ProfileRequestDto requestDto) {
    this.nickname = requestDto.getNickname();
    this.phoneNumber = requestDto.getPhoneNumber();
    this.interestArea = requestDto.getInterestArea();
  }


  public void changeUserEnum(UserStatus status){
    this.status = status;
  }
}
