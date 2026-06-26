package com.yamicode.lphiebackend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final List<Event> eventList = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Event> getAllEvents() {
        return eventList;
    }

    public Event createEvent(Event event){
        event.setId(idCounter++);
        eventList.add(event);
        return event;
    }

    public Event updateEvent(Long id, Event updatedEvent){

        for(Event e : eventList) {
            if(e.getId().equals(id)){
                e.setTitle(updatedEvent.getTitle());
                e.setDate(updatedEvent.getDate());
            }
        }
        return null;
    }

    public boolean deleteEvent(Long id){
        return eventList.removeIf(event -> event.getId().equals(id));
    }
}
