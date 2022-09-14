package com.example.vabatahtlikud.domain.event.location.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountyRepository extends JpaRepository<County, Integer> {
    @Query("select c from County c order by c.name")
    List<County> findAll();



}