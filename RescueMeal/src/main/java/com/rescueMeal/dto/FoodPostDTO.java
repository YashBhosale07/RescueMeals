package com.rescueMeal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodPostDTO {

    private String restaurantName;
    private String program;
    private String foodDescription;
    private Integer quantity;
    private double latitude;
    private double longitude;
    private DonorDTO donorDTO;


}
