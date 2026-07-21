package com.yamicode.lphiebackend.Services;

import com.yamicode.lphiebackend.Models.Event;
import com.yamicode.lphiebackend.Repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event event){
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent){
        Event event = getEventById(id);

        event.setTitle(updatedEvent.getTitle());
        event.setDate(updatedEvent.getDate());
        return eventRepository.save(event);
    }

    public Event getEventById(Long id){
        return eventRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Event with id " + id + " does not exist")
        );
    }

    public boolean deleteEvent(Long id){
        if (!eventRepository.existsById(id)) {
            return false;
        }

        eventRepository.deleteById(id);
        return true;
    }
}
