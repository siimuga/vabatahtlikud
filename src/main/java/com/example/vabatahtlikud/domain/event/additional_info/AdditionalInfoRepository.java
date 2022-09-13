package com.example.vabatahtlikud.domain.event.additional_info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Integer> {
    @Query("select (count(a) > 0) from AdditionalInfo a where a.name = ?1 and a.status = true")
    boolean existsByNameAndStatusTrue(String name);

    @Query("select (count(a) > 0) from AdditionalInfo a where a.eventRegister.id = ?1 and a.name = ?2")
    boolean existsByEventRegisterIdAndName(Integer id, String name);

    @Query("select (count(a) > 0) from AdditionalInfo a where upper(a.name) = upper(?1)")
    boolean existsByName(String name);

    @Query("select (count(a) > 0) from AdditionalInfo a where a.eventRegister.id = ?1 and a.status = true")
    boolean existsByEventRegisterIdAndStatusTrue(Integer id);

    @Query("""
            select (count(a) > 0) from AdditionalInfo a
            where upper(a.name) = upper(?1) and a.eventRegister.id = ?2 and a.status = true""")
    boolean existsByNameAndEventRegisterIdAndStatus(String name, Integer id);


    @Query("select a from AdditionalInfo a where a.eventRegister.id = ?1 and a.status = true")
    List<AdditionalInfo> findByStatusTrueAndEventRegisterId(Integer id);







}