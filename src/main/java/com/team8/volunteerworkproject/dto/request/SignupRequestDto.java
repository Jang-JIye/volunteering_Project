package com.team8.volunteerworkproject.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {

  @Size(min = 10, max = 50, message = "아이디로 사용하실 이메일 주소를 입력해주세요.")
  @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-z]+$", message = "아이디 형식에 맞춰 작성해주세요. 이메일 형식만 작성 가능합니다.")
  private String userId;

  @Size(min = 8, max = 15, message = "비밀번호는 8글자 이상, 15글자 이하로 작성하세요.")
  @Pattern(regexp = "^[0-9a-zA-Z!@#$%^&*()_=+?]*$", message = "영문(대, 소문자), 숫자, 특수문자를 혼합해주세요.") //혼합 안 해도 회원가입 가능
  private String password;

  private String nickname;

  private String adminToken;

  @Pattern(regexp = "^([0-9]{3})-?([0-9]{2})-?([0-9]{4})", message = "-(하이픈)를 포함하여 사업자 등록번호를 10자리를 입력해주세요.")
  private String companyRegisterNumb;


}
