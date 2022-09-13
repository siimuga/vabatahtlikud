package com.example.vabatahtlikud.domain.event.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRegisterRepository extends JpaRepository<EventRegister, Integer> {
}