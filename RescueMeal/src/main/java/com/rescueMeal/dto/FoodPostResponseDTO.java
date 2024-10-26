package com.rescueMeal.dto;

import com.rescueMeal.model.NGO;
import com.rescueMeal.model.User;
import com.rescueMeal.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodPostResponseDTO {
    private String foodDescription;
    private Integer quantity;
    private LocalDateTime postTime;
    private Status status ;
    private String ngoName;
    private String ngoContact;

}
