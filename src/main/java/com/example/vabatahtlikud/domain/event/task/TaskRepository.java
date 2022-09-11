package com.example.vabatahtlikud.domain.event.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("select (count(t) > 0) from Task t where t.name = ?1 and t.status = true")
    boolean existsByNameAndStatusTrue(String name);

    @Query("select t from Task t where t.status = true")
    List<Task> findAllByStatusTrue();




}