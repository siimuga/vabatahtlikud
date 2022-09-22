package com.example.vabatahtlikud.domain.event.volunteer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
//    @Query("select v from Volunteer v where v.user.id = ?1 order by v.event.startDate")
//    List<Volunteer> findByUser(Integer id);

    @Query("select (count(v) > 0) from Volunteer v where v.user.id = ?1 and v.event.id = ?2")
    boolean existsByIds(Integer id, Integer id1);

    @Query("select v from Volunteer v where v.user.id = ?1 and v.event.id = ?2")
    List<Volunteer> findByUserIdAndEventId(Integer id, Integer id1);

    @Query("select v from Volunteer v where v.user.id = ?1 and v.status = true order by v.event.startDate")
    List<Volunteer> findByUserIdAndStatus(Integer id);

    @Query("select v from Volunteer v where v.event.id = ?1")
    List<Volunteer> findByEvent_Id(Integer id);

    @Query("select (count(v) > 0) from Volunteer v where v.user.id = ?1 and v.event.id = ?2 and v.status = true")
    boolean existsByUserIdAndEventId(Integer id, Integer id1);





}