package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.enums.UserRoleEnum;
import com.team8.volunteerworkproject.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User extends Timestamp {

  @Id
  @Column(name = "user_id", nullable = false)
  private String userId;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String nickname;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UserRoleEnum role;

  private String companyRegisterNumb;

  public User(String userId, String password, String nickname, UserRoleEnum role,
      String companyRegisterNumb) {
    this.userId = userId;
    this.password = password;
    this.nickname = nickname;
    this.role = role;
    this.companyRegisterNumb = companyRegisterNumb;
  }

  public User(String userId, String password, String nickname, String companyRegisterNumb) {
    this.userId = userId;
    this.password = password;
    this.nickname = nickname;
    this.companyRegisterNumb = companyRegisterNumb;
  }

  public void changeRole(UserRoleEnum role) {
    this.role = role;
  }

  public void changeNickname(String nickname){
    this.nickname = nickname;
  }

  public boolean isValidId(String userId) {
    return this.userId.equals(userId);
  }

}
