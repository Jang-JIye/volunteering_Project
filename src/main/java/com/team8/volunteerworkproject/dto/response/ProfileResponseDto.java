package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileResponseDto {
    private String userId;
    private String phoneNumber;
    private String nickname;
    private String interestArea;
    private String image;


    public ProfileResponseDto(String userId, Profile profile) {
        this.userId = userId;
        this.phoneNumber = profile.getPhoneNumber();
        this.nickname = profile.getNickname();
        this.interestArea = profile.getInterestArea();
        this.image = profile.getImage();
    }

}
