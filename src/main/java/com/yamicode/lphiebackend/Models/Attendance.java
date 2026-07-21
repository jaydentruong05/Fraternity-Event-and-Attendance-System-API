package com.yamicode.lphiebackend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
        name = "attendance",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_attendance_member_event",
                columnNames = {"member_id", "event_id"}
        )
)
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    @NotNull
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    @NotNull
    private Event event;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AttendanceStatus status;

    public Attendance() {

    }



    public Attendance(Long id, Member member, Event event, AttendanceStatus status) {
        this.id = id;
        this.member = member;
        this.event = event;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Event getEvent() {
        return event;
    }

    public AttendanceStatus getStatus() {
        return status;
    }
}
