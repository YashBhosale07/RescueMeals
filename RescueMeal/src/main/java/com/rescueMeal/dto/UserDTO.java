package com.rescueMeal.dto;

import com.rescueMeal.utils.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Roles role;

}
