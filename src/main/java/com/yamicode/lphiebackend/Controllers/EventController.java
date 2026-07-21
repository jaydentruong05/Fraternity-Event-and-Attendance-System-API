package com.yamicode.lphiebackend.Controllers;


import com.yamicode.lphiebackend.Models.Event;
import com.yamicode.lphiebackend.Services.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createEvent(@Valid @RequestBody Event event) {
        try {
            Event  createdEvent = eventService.createEvent(event);
            return ResponseEntity.status(201).body(createdEvent);
    }catch (IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventsById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        if(event == null) {
            return ResponseEntity.status(404).body("Event with id " + id + " not found");
        }
        return ResponseEntity.ok(event);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @Valid @RequestBody Event event){
        try{
            Event updatedEvent = eventService.updateEvent(id,event);
            if(updatedEvent == null) {
                return ResponseEntity.status(404).body("Event with id " + id + " not found");
            }
            return ResponseEntity.ok(updatedEvent);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id){
        boolean deleted = eventService.deleteEvent(id);
        if(!deleted) {
            return ResponseEntity.status(404).body("Event with id " + id + " not found");
        }
        return ResponseEntity.ok("Event with id " + id + " is deleted");
    }
}
