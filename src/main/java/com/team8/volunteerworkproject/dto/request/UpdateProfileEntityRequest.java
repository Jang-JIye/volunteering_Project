package com.team8.volunteerworkproject.dto.request;

import lombok.Getter;

@Getter
public class UpdateProfileEntityRequest {

  private String nickname;
  private String phoneNumber;
  private String interestArea;
  private String image;

  public UpdateProfileEntityRequest(String nickname, String phoneNumber, String interestArea,
      String image) {
    this.nickname = nickname;
    this.phoneNumber = phoneNumber;
    this.interestArea = interestArea;
    this.image = image;
  }
}
