package com.yamicode.lphiebackend;


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
    public Event createEvent( @RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
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
