package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.Notice;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
  Optional<Notice> findByNoticeId(long noticeId);

}
