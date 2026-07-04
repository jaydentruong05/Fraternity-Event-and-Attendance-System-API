package com.yamicode.lphiebackend.Controllers;

import com.yamicode.lphiebackend.Models.Attendance;
import com.yamicode.lphiebackend.Services.AttendanceService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> markAttendance(@RequestBody Attendance attendance) {
        try {
            Attendance savedAttendance = attendanceService.markAttendance(attendance);
            return ResponseEntity.status(201).body(savedAttendance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Attendance>> getByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(attendanceService.getByEvent(eventId));
    }
}
