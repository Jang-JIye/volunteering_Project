package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.enums.StatusEnum;
import lombok.Getter;

@Getter
public class StatusResponseDto {
  private StatusEnum status;
  private String message;

  public StatusResponseDto(StatusEnum status, String message) {
    this.status = status;
    this.message = message;
  }
}
