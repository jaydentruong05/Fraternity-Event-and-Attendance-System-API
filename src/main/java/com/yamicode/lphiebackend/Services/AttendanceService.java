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

        Long memberId = attendance.getMember().getId();
        Long eventId = attendance.getEvent().getId();

        Member member = memberService.getMemberById(memberId);
        Event event = eventService.getEventById(eventId);

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
}
