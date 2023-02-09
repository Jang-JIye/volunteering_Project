package com.team8.volunteerworkproject.enums;


public enum UserRoleEnum {
  USER(Authority.USER),
  CORPORATION(Authority.CORPORATION),
  ADMIN(Authority.ADMIN);

  private final String authority;

  UserRoleEnum(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return this.authority;
  }

  public static class Authority {
    public static final String USER = "ROLE_USER";
    public static final String CORPORATION = "ROLE_CORPORATION";
    public static final String ADMIN = "ROLE_ADMIN";
  }
}
