package com.yamicode.lphiebackend.Services;

import com.yamicode.lphiebackend.Models.Attendance;
import com.yamicode.lphiebackend.Models.AttendanceStatus;
import com.yamicode.lphiebackend.Models.Event;
import com.yamicode.lphiebackend.Models.Member;
import com.yamicode.lphiebackend.Models.Role;
import com.yamicode.lphiebackend.Repository.AttendanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AttendanceServiceTests {

    private AttendanceRepository attendanceRepository;
    private MemberService memberService;
    private EventService eventService;
    private AttendanceService attendanceService;

    @BeforeEach
    void setUp() {
        attendanceRepository = mock(AttendanceRepository.class);
        memberService = mock(MemberService.class);
        eventService = mock(EventService.class);
        attendanceService = new AttendanceService(attendanceRepository, memberService, eventService);
    }

    @Test
    void markAttendanceSavesExistingMemberAndEvent() {
        Member member = new Member(1L, "Test", "Member", Role.MEMBER);
        Event event = new Event(2L, "Chapter Meeting", LocalDate.of(2026, 7, 21));
        Attendance request = new Attendance(null, new Member(1L, null, null, null),
                new Event(2L, null, null), AttendanceStatus.PRESENT);

        when(memberService.getMemberById(1L)).thenReturn(member);
        when(eventService.getEventById(2L)).thenReturn(event);
        when(attendanceRepository.findByMemberIdAndEventId(1L, 2L)).thenReturn(Optional.empty());
        when(attendanceRepository.save(request)).thenReturn(request);

        Attendance result = attendanceService.markAttendance(request);

        assertSame(member, result.getMember());
        assertSame(event, result.getEvent());
        assertEquals(AttendanceStatus.PRESENT, result.getStatus());
        verify(attendanceRepository).save(request);
    }

    @Test
    void markingSameMemberAndEventUpdatesExistingStatus() {
        Member member = new Member(1L, "Test", "Member", Role.MEMBER);
        Event event = new Event(2L, "Chapter Meeting", LocalDate.of(2026, 7, 21));
        Attendance existing = new Attendance(5L, member, event, AttendanceStatus.ABSENT);
        Attendance request = new Attendance(null, new Member(1L, null, null, null),
                new Event(2L, null, null), AttendanceStatus.EXCUSED_ABSENCE);

        when(memberService.getMemberById(1L)).thenReturn(member);
        when(eventService.getEventById(2L)).thenReturn(event);
        when(attendanceRepository.findByMemberIdAndEventId(1L, 2L))
                .thenReturn(Optional.of(existing));
        when(attendanceRepository.save(existing)).thenReturn(existing);

        Attendance result = attendanceService.markAttendance(request);

        assertEquals(5L, result.getId());
        assertEquals(AttendanceStatus.EXCUSED_ABSENCE, result.getStatus());
        verify(attendanceRepository).save(existing);
    }
}
