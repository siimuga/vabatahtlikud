package com.example.vabatahtlikud.domain.event.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c order by c.name")
    List<Category> findAll();

    @Query("select c from Category c where c.name = ?1")
    Category findByName(String name);




}