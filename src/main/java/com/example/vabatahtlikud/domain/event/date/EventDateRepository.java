package com.example.vabatahtlikud.domain.event.date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventDateRepository extends JpaRepository<EventDate, Integer> {
    @Query("select e from EventDate e where e.event.id = ?1")
    List<EventDate> findByEventId(Integer id);
}