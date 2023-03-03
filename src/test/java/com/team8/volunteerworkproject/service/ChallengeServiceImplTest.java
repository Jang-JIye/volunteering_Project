package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.ChallengeRequestDto;


import com.team8.volunteerworkproject.dto.response.ChallengeResponseDto;
import com.team8.volunteerworkproject.entity.Challenge;
import com.team8.volunteerworkproject.repository.ChallengeRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;




import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@Transactional
class ChallengeServiceImplTest {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    ChallengeServiceImpl challengeService;


    @Test
    public void 챌린지_작성() throws Exception {
        // given
        String title = "test1";
        String userId = "abc@naver.com";
        String content = "it's test";
        String image = "image";

        ChallengeRequestDto requestDto = ChallengeRequestDto.builder()
                .title(title)
                .userId(userId)
                .content(content)
                .image(image)
                .build();

        // when
        ChallengeResponseDto responseDto = challengeService.createChallenge(requestDto);

        // then
        Challenge savedChallenge = challengeRepository.findById(responseDto.getChallengeId()).orElse(null);
        assertThat(savedChallenge).isNotNull();
        assertThat(savedChallenge.getTitle()).isEqualTo(title);
        assertThat(savedChallenge.getUserId()).isEqualTo(userId);
        assertThat(savedChallenge.getContent()).isEqualTo(content);
        assertThat(savedChallenge.getImage()).isEqualTo(image);
    }


    @Test
    public void 챌린지_수정() {
        //given
        String title = "test1";
        String userId = "abc@naver.com";
        String content = "it's test";
        String image = "image";

        ChallengeRequestDto requestDto = ChallengeRequestDto.builder()
                .title(title)
                .userId(userId)
                .content(content)
                .image(image)
                .build();

        ChallengeResponseDto savedResponseDto = challengeService.createChallenge(requestDto);

        //when
        String updatedTitle = "test2";
        String updatedContent = "happy";
        String updatedImage = "image><";

        ChallengeRequestDto updatedRequestDto = ChallengeRequestDto.builder()
                .title(updatedTitle)
                .content(updatedContent)
                .image(updatedImage)
                .build();

        ChallengeResponseDto updatedResponseDto = challengeService.updateChallenge(savedResponseDto.getChallengeId(), updatedRequestDto);

        //then
        Challenge savedChallenge = challengeRepository.findById(savedResponseDto.getChallengeId()).orElse(null);
        assertThat(savedChallenge).isNotNull();
        assertThat(savedChallenge.getTitle()).isEqualTo(updatedTitle);
        assertThat(savedChallenge.getContent()).isEqualTo(updatedContent);
        assertThat(savedChallenge.getImage()).isEqualTo(updatedImage);
    }

    @Test
    public void 챌린지_수정_예외() {
        //given
        String title = "test1";
        String userId = "abc@naver.com";
        String content = "it's test";
        String image = "image";

        Long challengeId = 1000L;
        ChallengeRequestDto requestDto = ChallengeRequestDto.builder()
                .title(title)
                .userId(userId)
                .content(content)
                .image(image)
                .build();

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> challengeService.updateChallenge(challengeId, requestDto));

        String expectedErrorMessage = "수정할 챌린지가 없습니다.";
        String actualErrorMessage = exception.getMessage();

        //then
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void 챌린지_삭제() {
        //given
        String title = "test1";
        String userId = "test11";
        String content = "it's test";
        String image = "image";

        Long challengeId = 1000L;

        ChallengeRequestDto requestDto = ChallengeRequestDto.builder()
                .title(title)
                .userId(userId)
                .content(content)
                .image(image)
                .build();

        ChallengeResponseDto responseDto = challengeService.createChallenge(requestDto);

        //when
        challengeService.deleteChallenge(responseDto.getChallengeId());

        //then
        assertThat(challengeRepository.findById(responseDto.getChallengeId())).isEmpty();
    }


    @Test
    public void 챌린지_삭제_예외() {
        //given
        Long challengeId = -1L; // 유효하지 않은 challengeId 설정

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    challengeService.deleteChallenge(challengeId);
                });
        String expectedMessage = "삭제할 챌린지가 존재하지 않습니다.";
        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
