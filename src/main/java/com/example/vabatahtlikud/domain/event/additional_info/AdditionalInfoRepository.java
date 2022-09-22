package com.example.vabatahtlikud.domain.event.additional_info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Integer> {
    @Query("""
            select (count(a) > 0) from AdditionalInfo a
            where upper(a.name) = upper(?1) and a.status = true and a.event.id = ?2""")
    boolean existsByNameAndEventIdAndStatus(String name, Integer id);

    @Query("select a from AdditionalInfo a where a.status = true and a.event.id = ?1")
    List<AdditionalInfo> findByStatusTrueAndEventId(Integer id);


}