package com.yamicode.lphiebackend.Repository;

import com.yamicode.lphiebackend.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
