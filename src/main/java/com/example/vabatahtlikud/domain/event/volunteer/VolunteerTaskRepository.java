package com.example.vabatahtlikud.domain.event.volunteer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerTaskRepository extends JpaRepository<VolunteerTask, Integer> {
}