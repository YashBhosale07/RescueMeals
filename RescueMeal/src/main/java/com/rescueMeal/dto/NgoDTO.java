package com.rescueMeal.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NgoDTO {

    private String name;
    private String contact;
    private Integer capacity;
    private Double latitude;
    private Double longitude;
}
