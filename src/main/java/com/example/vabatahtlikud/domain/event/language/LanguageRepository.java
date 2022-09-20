package com.example.vabatahtlikud.domain.event.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    @Query("select l from Language l where l.name = ?1")
    Language findByName(String name);


}