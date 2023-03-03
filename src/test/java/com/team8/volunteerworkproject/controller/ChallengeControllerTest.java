package com.team8.volunteerworkproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team8.volunteerworkproject.dto.request.ChallengeRequestDto;
import com.team8.volunteerworkproject.dto.response.AllChallengeResponseDto;
import com.team8.volunteerworkproject.dto.response.ChallengeResponseDto;
import com.team8.volunteerworkproject.entity.Challenge;

import com.team8.volunteerworkproject.service.ChallengeServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ChallengeControllerTest {

    @Mock
    private ChallengeServiceImpl challengeService;
    @InjectMocks
    private ChallengeController challengeController;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void 챌린지_작성() throws Exception {
        //given
        ChallengeRequestDto requestDto = ChallengeRequestDto.builder()
                .userId("admin")
                .title("Test")
                .content("content")
                .image("image")
                .build();

        Challenge challenge = new Challenge(requestDto.getUserId(), requestDto.getTitle(), requestDto.getContent(), requestDto.getImage());
        ChallengeResponseDto responseDto = new ChallengeResponseDto(challenge);
        when(challengeService.createChallenge(any(ChallengeRequestDto.class))).thenReturn(responseDto);

        //when
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(challengeController).build();

        mockMvc.perform(post("/admin/challenges")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.challengeId").value(challenge.getChallengeId()))
                .andExpect(jsonPath("$.title").value(challenge.getTitle()))
                .andExpect(jsonPath("$.content").value(challenge.getContent()))
                .andExpect(jsonPath("$.image").value(challenge.getImage()));

        //then
        verify(challengeService, times(1)).createChallenge(any(ChallengeRequestDto.class));
        verifyNoMoreInteractions(challengeService);
    }


     @Test
     public void 챌린지_수정() throws Exception{
        //given
         Long challengeId = 1L;
         ChallengeRequestDto requestDto = ChallengeRequestDto.builder()
                 .userId("admin")
                 .title("Test")
                 .content("update content")
                 .image("image")
                 .build();

         Challenge challenge = new Challenge(requestDto.getUserId(), requestDto.getTitle(), requestDto.getContent(), requestDto.getImage());

         ChallengeResponseDto responseDto = new ChallengeResponseDto(challenge);

         when(challengeService.updateChallenge(challengeId,requestDto)).thenReturn(responseDto);


         //when
         MockMvc mockMvc = MockMvcBuilders.standaloneSetup(challengeController).build();
         mockMvc.perform(patch("/admin/challenges/{challengeId}",challengeId)
                         .contentType(MediaType.APPLICATION_JSON)
                         .content(objectMapper.writeValueAsString(requestDto)))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(jsonPath("$.challengeId").value(challenge.getChallengeId()))
                 .andExpect(jsonPath("$.title").value(challenge.getTitle()))
                 .andExpect(jsonPath("$.content").value(challenge.getContent()))
                 .andExpect(jsonPath("$.image").value(challenge.getImage()));

         //then
         verify(challengeService).updateChallenge(challengeId,requestDto);
         verifyNoMoreInteractions(challengeService);

     }

    @Test
    public void 챌린지_삭제() throws Exception {
        //given
        Long challengeId = 1L;
        String returnValue = "Deleted challenge with id " + challengeId;
        doReturn(returnValue).when(challengeService).deleteChallenge(challengeId);

        //when
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(challengeController).build();
        ResultActions resultActions = mockMvc.perform(delete("/admin/challenges/{challengeId}", challengeId));
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(content().string(returnValue));

        //then
        verify(challengeService).deleteChallenge(challengeId);
        verifyNoMoreInteractions(challengeService);
    }

    @Test
    public void 챌린지_전체조회() throws Exception {
        // given
        List<AllChallengeResponseDto> challengeList = new ArrayList<>();
        challengeList.add(new AllChallengeResponseDto());
        challengeList.add(new AllChallengeResponseDto());
        given(challengeService.getAllChallenge()).willReturn(challengeList);

        // when
        ResponseEntity<List<AllChallengeResponseDto>> response = challengeController.getAllChallenge();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(challengeList, response.getBody());

    }

    @Test
    public void 챌린지_선택조회() throws Exception {
        // given
        Long challengeId = 1L;
        ChallengeResponseDto challengeResponseDto = new ChallengeResponseDto();

        when(challengeService.getChallenge(challengeId)).thenReturn(challengeResponseDto);

        // when
        ResponseEntity<ChallengeResponseDto> response = challengeController.getChallege(challengeId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(challengeResponseDto, response.getBody());
    }

//    @Test
//    public void 챌린지_선택조회_예외() throws Exception {
//        // given
//        Long challengeId = 1L;
//
//        when(challengeService.getChallenge(challengeId)).thenThrow(new IllegalArgumentException("챌린지 조회 실패"));
//
//        // when
//        ResponseEntity<ChallengeResponseDto> response = challengeController.getChallege(challengeId);
//
//        // then
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertNull(response.getBody());
//    }

}



