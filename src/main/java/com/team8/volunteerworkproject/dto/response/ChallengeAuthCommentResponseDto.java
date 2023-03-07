package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.ChallengeAuthComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChallengeAuthCommentResponseDto {

    private Long challengeAuthCommentId;
    private Long challengeAuthId;
    private String content;
    private String userId; //일단 유저 아이디 받아오고 리팩토링할 때 닉넥임으로 변경 예정


    public ChallengeAuthCommentResponseDto(ChallengeAuthComment challengeAuthComments) {
        this.challengeAuthCommentId = challengeAuthComments.getChallengeAuthCommentId();
        this.challengeAuthId = challengeAuthComments.getChallengeAuthId();
        this.content = challengeAuthComments.getContent();
        this.userId = challengeAuthComments.getUserId();
    }
}
