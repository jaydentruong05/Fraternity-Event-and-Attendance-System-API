package com.yamicode.lphiebackend.Controllers;

import com.yamicode.lphiebackend.Models.Attendance;
import com.yamicode.lphiebackend.Services.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        return attendanceService.markAttendance(attendance);
    }

    @GetMapping("/event/{eventId}")
    public List<Attendance> getByEvent(@PathVariable Long eventId) {
        return attendanceService.getByEvent(eventId);
    }
}
