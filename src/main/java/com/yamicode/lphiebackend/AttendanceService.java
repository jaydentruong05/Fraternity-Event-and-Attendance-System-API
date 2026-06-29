package com.yamicode.lphiebackend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {

    private final List<Attendance> attendanceList = new ArrayList<>();
    private Long idCounter = 1L;

    public Attendance markAttendance(Attendance attendance) {
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
