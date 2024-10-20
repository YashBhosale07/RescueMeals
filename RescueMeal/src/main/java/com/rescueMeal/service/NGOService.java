package com.rescueMeal.service;

import com.rescueMeal.dto.NgoDTO;
import com.rescueMeal.model.NGO;

public interface NGOService {

    long registerNGO(NgoDTO ngo);
    NGO findById(Long id);


}
