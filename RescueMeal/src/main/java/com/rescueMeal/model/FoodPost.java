package com.rescueMeal.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rescueMeal.Configuration.PointSerializer;
import com.rescueMeal.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_post")
public class FoodPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "program_name")
    private String programName;

    @Column(name = "food_description", nullable = false)
    private String foodDescription;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @JsonSerialize(using = PointSerializer.class)
    @Column(name = "location", nullable = false)
    private Point location;

    @Column(name = "post_time", nullable = false)
    private LocalDateTime postTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status =Status.OPEN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ngo_assigned_id", referencedColumnName = "id")
    private NGO ngoAssigned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id", referencedColumnName = "id")
    private Donor donor;
}
