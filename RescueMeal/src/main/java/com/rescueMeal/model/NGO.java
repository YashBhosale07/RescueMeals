package com.rescueMeal.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rescueMeal.Configuration.PointSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;


import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ngo")
public class NGO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact", nullable = false)
    private String contact;

    @JsonSerialize(using = PointSerializer.class)
    @Column(name = "location", nullable = false)
    private Point location;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

}
