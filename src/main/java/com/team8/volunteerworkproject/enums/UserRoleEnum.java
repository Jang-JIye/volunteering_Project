package com.team8.volunteerworkproject.enums;


public enum UserRoleEnum {
  USER(Authority.USER),
  COMPANY(Authority.COMPANY),
  ADMIN(Authority.ADMIN),
  UNREGISTER(Authority.UNREGISTER);


  private final String authority;

  UserRoleEnum(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return this.authority;
  }

  public static class Authority {
    public static final String USER = "ROLE_USER";
    public static final String COMPANY = "ROLE_COMPANY";
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String UNREGISTER = "ROLE_UNREGISTER";
  }

}
