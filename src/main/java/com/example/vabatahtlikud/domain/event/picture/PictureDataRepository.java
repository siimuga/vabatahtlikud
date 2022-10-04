package com.example.vabatahtlikud.domain.event.picture;

import com.example.vabatahtlikud.domain.event.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PictureDataRepository extends JpaRepository<PictureData, Integer> {

    @Query("select p from PictureData p where p.event.id = ?1")
    Optional<PictureData> findByEventId(Integer eventId);



}