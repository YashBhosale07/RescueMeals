package com.rescueMeal.dto;

import com.rescueMeal.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NgoResponseDTO {
    private String foodDescription;
    private Integer quantity;
    private String donorName;
    private String donorEmail;
    private Point location;
}