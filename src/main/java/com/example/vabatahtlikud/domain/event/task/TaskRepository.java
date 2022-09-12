package com.example.vabatahtlikud.domain.event.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

 /*   @Query("select (count(t) > 0) from Task t where upper(t.name) = upper(?1)")
    boolean existsByName(String name);

    @Query("select (count(t) > 0) from Task t where t.eventRegister.id = ?1 and t.status = true")
    boolean existsByEventRegisterIdAndStatusTrue(Integer id);

    @Query("select (count(t) > 0) from Task t where t.name = ?1 and t.eventRegister.id = ?2 and t.status = true")
    boolean existsByNameAndEventRegisterIdAndStatus222(String name, Integer id);*/

    @Query("""
            select (count(t) > 0) from Task t
            where upper(t.name) = upper(?1) and t.eventRegister.id = ?2 and t.status = true""")
    boolean existsByNameAndEventRegisterIdAndStatus(String name, Integer id);


    @Query("select t from Task t where t.status = true and t.eventRegister.id = ?1")
    List<Task> findByStatusTrueAndEventRegisterId(Integer id);




}