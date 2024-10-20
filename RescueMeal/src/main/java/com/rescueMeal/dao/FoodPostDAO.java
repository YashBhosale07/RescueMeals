package com.rescueMeal.dao;

import com.rescueMeal.model.FoodPost;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodPostDAO extends JpaRepository<FoodPost,Long> {

    @Query(value = "SELECT * FROM food_post f WHERE ST_Distance_Sphere(f.location, :ngoLocation) < :distance", nativeQuery = true)
    List<FoodPost> findNearByFoodPostsForNGO(@Param("ngoLocation") Point ngoLocation, @Param("distance") double distanceInMeters);
}
