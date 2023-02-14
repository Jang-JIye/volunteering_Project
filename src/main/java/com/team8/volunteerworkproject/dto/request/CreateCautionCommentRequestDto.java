package com.team8.volunteerworkproject.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCautionCommentRequestDto {

  @NonNull
  private final String cautionReason;

  @NonNull
  private final String userId;

}
