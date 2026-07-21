package com.yamicode.lphiebackend.Controllers;

import com.yamicode.lphiebackend.Models.Attendance;
import com.yamicode.lphiebackend.Services.AttendanceService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> markAttendance(@Valid @RequestBody Attendance attendance) {
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

    @GetMapping
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance) {
        try {
            Attendance updatedAttendance = attendanceService.updateAttendance(id, attendance);
            if (updatedAttendance == null) {
                return ResponseEntity.status(404).body("Attendance with id " + id + " not found");
            }
            return ResponseEntity.ok(updatedAttendance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long id) {
        boolean deleted = attendanceService.deleteAttendance(id);
        if (!deleted) {
            return ResponseEntity.status(404).body("Attendance with id " + id + " not found");
        }
        return ResponseEntity.ok("Attendance with id " + id + " is deleted");
    }
}
