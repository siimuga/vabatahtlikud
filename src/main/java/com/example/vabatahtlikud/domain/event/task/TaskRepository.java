package com.example.vabatahtlikud.domain.event.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("select (count(t) > 0) from Task t where upper(t.name) = upper(?1) and t.event.id = ?2 and t.status = true")
    boolean existsByNameAndEventIdAndStatus(String name, Integer id);

    @Query("select t from Task t where t.status = true and t.event.id = ?1")
    List<Task> findByStatusTrueAndEventId(Integer id);

    @Query("select t from Task t where t.event.id = ?1 and t.status = true order by t.name")
    List<Task> findByEventIdAndStatus(Integer id);












}