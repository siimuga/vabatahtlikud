package com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VolunteerEventDateRepository extends JpaRepository<VolunteerEventDate, Integer> {
    @Query("select v from VolunteerEventDate v where v.volunteer.id = ?1")
    List<VolunteerEventDate> findByVolunteerId(Integer id);

    @Query("select v from VolunteerEventDate v where v.volunteer.id = ?1")
    Optional<VolunteerEventDate> findByVolunteer_Id(Integer id);
}