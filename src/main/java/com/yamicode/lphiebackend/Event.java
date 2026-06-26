package com.yamicode.lphiebackend;

import java.time.LocalDateTime;

public class Event {

    private Long id;
    private String title;
    private LocalDateTime date;

    public Event () {

    }

    public Event(Long id, String title, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
