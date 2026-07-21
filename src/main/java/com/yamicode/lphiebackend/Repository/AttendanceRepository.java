package com.yamicode.lphiebackend.Repository;

import com.yamicode.lphiebackend.Models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByEventId(Long eventId);

    Optional<Attendance> findByMemberIdAndEventId(Long memberId, Long eventId);
}
