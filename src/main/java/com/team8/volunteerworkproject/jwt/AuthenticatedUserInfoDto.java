package com.team8.volunteerworkproject.jwt;

import com.team8.volunteerworkproject.enums.UserRoleEnum;
import lombok.Getter;

@Getter
public class AuthenticatedUserInfoDto {
  private UserRoleEnum role;
  private String username;

  public AuthenticatedUserInfoDto(UserRoleEnum role, String username) {
    this.role = role;
    this.username = username;
  }
}
