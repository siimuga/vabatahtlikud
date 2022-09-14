package com.example.vabatahtlikud.domain.event.event;

import com.example.vabatahtlikud.domain.event.additional_info.AdditionalInfo;
import com.example.vabatahtlikud.domain.event.category.Category;
import com.example.vabatahtlikud.domain.event.language.Language;
import com.example.vabatahtlikud.domain.event.location.Location;
import com.example.vabatahtlikud.domain.event.picture.PictureData;
import com.example.vabatahtlikud.domain.event.task.Task;
import com.example.vabatahtlikud.domain.user.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "volunteers_required", nullable = false)
    private Integer volunteersRequired;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(name = "status", nullable = false, length = 1)
    private String status;

    @Column(name = "link")
    private String link;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "picture_data_id")
//    private PictureData pictureData;

}
