package com.team8.volunteerworkproject.enums;

public enum StatusEnum {
  OK(200, "OK"),
  BAD_REQUEST(400, "BAD_REQUEST"),
  UNAUTHORIZED(401, "UNAUTHORIZED"),
  FORBIDDEN(403, "FORBIDDEN"),
  NOT_FOUND(404, "NOT_FOUND"),
  INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");

  final int statusCode;
  final String code;

  StatusEnum(int statusCode, String code) {
    this.statusCode = statusCode;
    this.code = code;
  }
}
