package com.rescueMeal.service.impl;
import com.rescueMeal.dao.FoodPostDAO;
import com.rescueMeal.dto.FoodPostDTO;
import com.rescueMeal.exceptionClasses.FoodPostNotCreatedException;
import com.rescueMeal.model.FoodPost;
import com.rescueMeal.service.FoodPostService;
import org.hibernate.exception.ConstraintViolationException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FoodPostServiceImpl implements FoodPostService {

    @Autowired
    private FoodPostDAO foodPostDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public long createPost(FoodPostDTO foodPostDTO) {
        try {
            GeometryFactory geometryFactory = new GeometryFactory();
            double latitude = foodPostDTO.getLatitude();
            double longitude = foodPostDTO.getLongitude();
            Point location = geometryFactory.createPoint(new Coordinate(longitude, latitude));

            FoodPost foodPost=modelMapper.map(foodPostDTO,FoodPost.class);
            foodPost.setPostTime(LocalDateTime.now());
            foodPost.setLocation(location);
            System.out.println(location.toString());
            foodPostDAO.save(foodPost);
            return foodPost.getId();
        } catch (ConstraintViolationException e) {
            throw new FoodPostNotCreatedException("Food Post was not created due to validation failure: " + e.getMessage());
        } catch (Exception e) {
            throw new FoodPostNotCreatedException("An error occurred while creating FoodPost: " + e.getMessage());
        }
    }
    public List<FoodPost> getNearbyFoodPostsForNGO(Point ngoLocation, double radiusInKm) {
        double distanceInMeters = radiusInKm * 1000;
        return foodPostDAO.findNearByFoodPostsForNGO(ngoLocation, distanceInMeters);
    }
}
