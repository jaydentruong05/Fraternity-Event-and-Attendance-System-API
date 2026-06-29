package com.yamicode.lphiebackend;

public class Attendance {

    private Long id;

    private Member member;
    private Event event;
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
