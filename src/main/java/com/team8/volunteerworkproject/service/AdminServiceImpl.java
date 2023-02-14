package com.team8.volunteerworkproject.service;

import com.team8.volunteerworkproject.dto.request.NoticeRequestDto;
import com.team8.volunteerworkproject.dto.response.NoticeResponseDto;
import com.team8.volunteerworkproject.entity.Notice;
import com.team8.volunteerworkproject.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final NoticeRepository noticeRepository;

    //공지사항 작성
    @Override
    @Transactional
    public NoticeResponseDto createNotice(String userId, NoticeRequestDto requestDto) {
        Notice notice = new Notice(requestDto, userId);
        noticeRepository.save(notice);
        return new NoticeResponseDto(userId, notice);

    }

    //공지사항 전체 조회
    @Override
    @Transactional
    public List<NoticeResponseDto> getNoticeList() {

        List<Notice> notices = noticeRepository.findAllByOrderByModifiedAtDesc();
        List<NoticeResponseDto> noticeResponseDtolist = new ArrayList<>();


        for (Notice notice : notices) {
            noticeResponseDtolist.add(new NoticeResponseDto(notice));
        }
        return noticeResponseDtolist;
    }
    //공지사항 선택 조회
    @Override
    @Transactional
    public  NoticeResponseDto findNotice(Long noticeId,String userId){
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(
                () -> new IllegalArgumentException("조회하려는 공지사항이 없습니다.")
        );
        return  new NoticeResponseDto(notice);
    }


    //공지사항 수정
    @Override
    @Transactional
    public NoticeResponseDto updateNotice(Long noticeId, NoticeRequestDto requestDto,String userId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다."));

        notice.update(requestDto.getTitle(), requestDto.getContent());

        noticeRepository.save(notice);

        return new NoticeResponseDto(notice);
    }





    //공지사항 삭제
        @Override
        @Transactional
        public NoticeResponseDto deleteNotice (Long noticeId, String userId){
            Notice notice = noticeRepository.findById(noticeId).orElseThrow(
                    () -> new IllegalArgumentException("삭제하고자 하는 공지사항이 없습니다.")
            );
            noticeRepository.delete(notice);
            return new NoticeResponseDto(notice);

        }


    }

