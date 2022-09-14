package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("select e from Event e where e.category.id = ?1 and e.location.county.id = ?2")
    List<Event> findByCategoryIdAndCountyId(Integer categoryId, Integer countyId);

    @Query("select e from Event e where e.location.county.id = ?1")
    List<Event> findByCountyId(Integer countyId);

    @Query("select e from Event e where e.category.id = ?1")
    List<Event> findByCategoryId(Integer categoryId);










}