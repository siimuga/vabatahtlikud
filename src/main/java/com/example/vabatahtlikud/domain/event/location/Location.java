package com.example.vabatahtlikud.domain.event.location;

import com.example.vabatahtlikud.domain.event.location.country.County;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "county_id", nullable = false)
    private County county;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "longtitude", precision = 8, scale = 6)
    private BigDecimal longtitude;

    @Column(name = "latitude", precision = 8, scale = 6)
    private BigDecimal latitude;


}