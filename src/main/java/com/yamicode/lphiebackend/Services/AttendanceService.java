package com.yamicode.lphiebackend.Services;

import com.yamicode.lphiebackend.Models.Attendance;
import com.yamicode.lphiebackend.Models.Event;
import com.yamicode.lphiebackend.Models.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {

    private final List<Attendance> attendanceList = new ArrayList<>();
    private Long idCounter = 1L;

    private final MemberService memberService;
    private final EventService eventService;

    public AttendanceService(MemberService memberService, EventService eventService) {
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

        if (member == null) {
            throw new IllegalArgumentException(
                    "Member with id " + memberId + " does not exist."
            );
        }

        if (event == null) {
            throw new IllegalArgumentException(
                    "Event with id " + eventId + " does not exist."
            );
        }

        attendance.setMember(member);
        attendance.setEvent(event);


        attendance.setId(idCounter++);
        attendanceList.add(attendance);

        return attendance;
    }

    public List<Attendance> getByEvent(Long id) {

        List<Attendance> result = new ArrayList<>();

        for (Attendance a : attendanceList) {
            if (a.getEvent().getId().equals(id)) {
                result.add(a);
            }
        }
        return result;
    }

    public List<Attendance> getAllAttendance() {
        return attendanceList;
    }

    public Attendance getById(Long id) {
        for (Attendance a : attendanceList) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
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
            if (member == null) {
                throw new IllegalArgumentException(
                        "Member with id " + updatedAttendance.getMember().getId() + " does not exist."
                );
            }
            existing.setMember(member);
        }

        if (updatedAttendance.getEvent() != null && updatedAttendance.getEvent().getId() != null) {
            Event event = eventService.getEventById(updatedAttendance.getEvent().getId());
            if (event == null) {
                throw new IllegalArgumentException(
                        "Event with id " + updatedAttendance.getEvent().getId() + " does not exist."
                );
            }
            existing.setEvent(event);
        }

        return existing;
    }

    public boolean deleteAttendance(Long id) {
        return attendanceList.removeIf(attendance -> attendance.getId().equals(id));
    }
}
