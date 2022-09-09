package com.example.vabatahtlikud.domain.user.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    @Query("select (count(c) > 0) from Contact c where c.email = ?1")
    boolean existsByEmail(String email);

    @Query("select count(c) from Contact c where c.email = ?1")
    long countByEmail(String email);




}