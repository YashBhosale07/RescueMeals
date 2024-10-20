package com.rescueMeal.service;

import com.rescueMeal.dto.FoodPostDTO;
import com.rescueMeal.model.FoodPost;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FoodPostService {

    long createPost(FoodPostDTO foodPost);
    List<FoodPost> getNearbyFoodPostsForNGO(Point ngoLocation, double radiusInKm);

}
