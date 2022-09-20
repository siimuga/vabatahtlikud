package com.example.vabatahtlikud.domain.event.location.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CountyRepository extends JpaRepository<County, Integer> {
    @Query("select c from County c order by c.name")
    List<County> findAll();

    @Query("select c from County c where c.name = ?1")
    County findByName(String name);







}