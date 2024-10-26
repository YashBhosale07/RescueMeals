package com.rescueMeal.service;

import com.rescueMeal.dto.NgoDTO;
import com.rescueMeal.dto.NgoResponseDTO;
import com.rescueMeal.model.NGO;

public interface NGOService {


    NgoResponseDTO acceptThePost(Long id);

    NGO findById(Long ngoId);
}
