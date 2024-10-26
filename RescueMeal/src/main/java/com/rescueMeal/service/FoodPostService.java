package com.rescueMeal.service;

import com.rescueMeal.dto.FoodPostDTO;
import com.rescueMeal.dto.FoodPostResponseDTO;
import com.rescueMeal.model.FoodPost;
import org.locationtech.jts.geom.Point;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FoodPostService {

    long createPost(FoodPostDTO foodPost);
    List<FoodPostResponseDTO> getNearbyFoodPostsForNGO(Point ngoLocation, double radiusInKm);

    List<FoodPostResponseDTO> getAllFoodPosted();

    FoodPostResponseDTO checkTheStatus(Long id);
}
