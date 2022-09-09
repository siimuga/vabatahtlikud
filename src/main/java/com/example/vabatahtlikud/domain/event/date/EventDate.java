package com.example.vabatahtlikud.domain.event.date;

import com.example.vabatahtlikud.domain.event.event.Event;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "event_date")
public class EventDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "volunteers_required", nullable = false)
    private Integer volunteersRequired;

    @Column(name = "volunteers_assigned", nullable = false)
    private Integer volunteersAssigned;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;


}