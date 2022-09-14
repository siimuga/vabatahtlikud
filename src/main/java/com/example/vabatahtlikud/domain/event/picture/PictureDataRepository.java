package com.example.vabatahtlikud.domain.event.picture;

import com.example.vabatahtlikud.domain.event.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PictureDataRepository extends JpaRepository<PictureData, Integer> {
    @Query("select p from PictureData p where p.event = ?1")
    PictureData findByEvent(Event event);


}