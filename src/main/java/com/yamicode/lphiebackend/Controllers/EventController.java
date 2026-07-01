package com.yamicode.lphiebackend.Controllers;


import com.yamicode.lphiebackend.Models.Event;
import com.yamicode.lphiebackend.Services.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event){
        return eventService.updateEvent(id,event);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEvent(@PathVariable Long id){
        return eventService.deleteEvent(id);
    }
}
