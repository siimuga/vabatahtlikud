package com.example.vabatahtlikud.domain.event.voulenteer;

import com.example.vabatahtlikud.domain.event.task.Task;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "volunteer_task")
public class VolunteerTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

}