package com.yamicode.lphiebackend.Services;

import com.yamicode.lphiebackend.Models.Attendance;
import com.yamicode.lphiebackend.Models.Event;
import com.yamicode.lphiebackend.Models.Member;
import com.yamicode.lphiebackend.Repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final MemberService memberService;
    private final EventService eventService;

    public AttendanceService(AttendanceRepository attendanceRepository,
                             MemberService memberService,
                             EventService eventService) {
        this.attendanceRepository = attendanceRepository;
        this.memberService = memberService;
        this.eventService = eventService;
    }



    public Attendance markAttendance(Attendance attendance) {

        if (attendance.getMember() == null) {
            throw new IllegalArgumentException("Member is required.");
        }

        if (attendance.getMember().getId() == null) {
            throw new IllegalArgumentException("Member id is required.");
        }

        if (attendance.getEvent() == null) {
            throw new IllegalArgumentException("Event is required.");
        }

        if (attendance.getEvent().getId() == null) {
            throw new IllegalArgumentException("Event id is required.");
        }

        if (attendance.getStatus() == null) {
            throw new IllegalArgumentException("Attendance status is required.");
        }

        Long memberId = attendance.getMember().getId();
        Long eventId = attendance.getEvent().getId();

        Member member = memberService.getMemberById(memberId);
        Event event = eventService.getEventById(eventId);

        Attendance savedAttendance = attendanceRepository
                .findByMemberIdAndEventId(memberId, eventId)
                .orElse(attendance);

        savedAttendance.setMember(member);
        savedAttendance.setEvent(event);
        savedAttendance.setStatus(attendance.getStatus());
        return attendanceRepository.save(savedAttendance);
    }

    public List<Attendance> getByEvent(Long id) {
        eventService.getEventById(id);
        return attendanceRepository.findByEventId(id);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public Attendance getById(Long id) {
        return attendanceRepository.findById(id).orElse(null);
    }

    public Attendance updateAttendance(Long id, Attendance updatedAttendance) {
        Attendance existing = getById(id);
        if (existing == null) {
            return null;
        }

        if (updatedAttendance.getStatus() != null) {
            existing.setStatus(updatedAttendance.getStatus());
        }

        if (updatedAttendance.getMember() != null && updatedAttendance.getMember().getId() != null) {
            Member member = memberService.getMemberById(updatedAttendance.getMember().getId());
            existing.setMember(member);
        }

        if (updatedAttendance.getEvent() != null && updatedAttendance.getEvent().getId() != null) {
            Event event = eventService.getEventById(updatedAttendance.getEvent().getId());
            existing.setEvent(event);
        }

        return attendanceRepository.save(existing);
    }

    public boolean deleteAttendance(Long id) {
        if (!attendanceRepository.existsById(id)) {
            return false;
        }

        attendanceRepository.deleteById(id);
        return true;
    }
}
