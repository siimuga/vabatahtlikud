package com.example.vabatahtlikud.domain.event.volunteer;

import com.example.vabatahtlikud.domain.event.date.EventDate;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "volunteer_event_date")
public class VolunteerEventDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_date_id", nullable = false)
    private EventDate eventDate;


}