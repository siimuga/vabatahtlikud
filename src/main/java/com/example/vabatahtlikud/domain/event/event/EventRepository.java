package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("select e from Event e where e.status like ?1 order by e.startDate")
    List<Event> findAll(String status);



    @Query("""
            select e from Event e
            where e.category.id = ?1 and e.location.county.id = ?2 and e.status like ?3
            order by e.startDate""")
    List<Event> findByCategoryIdAndCountyId(Integer id, Integer id1, String status);

    @Query("select e from Event e where e.location.county.id = ?1 and e.status like ?2 order by e.startDate")
    List<Event> findByCountyId(Integer countyId, String status);


    @Query("select e from Event e where e.category.id = ?1 and e.status like ?2 order by e.startDate")
    List<Event> findByCategoryId(Integer id, String status);










}