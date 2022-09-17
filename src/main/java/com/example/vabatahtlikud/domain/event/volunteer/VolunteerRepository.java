package com.example.vabatahtlikud.domain.event.volunteer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
    @Query("select v from Volunteer v where v.user.id = ?1 order by v.event.startDate")
    List<Volunteer> findByUser(Integer id);



}