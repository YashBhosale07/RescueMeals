package com.rescueMeal.service.impl;
import com.rescueMeal.dao.FoodPostDAO;
import com.rescueMeal.dto.FoodPostDTO;
import com.rescueMeal.dto.FoodPostResponseDTO;
import com.rescueMeal.exceptionClasses.FoodPostNotCreatedException;
import com.rescueMeal.exceptionClasses.FoodPostNotFound;
import com.rescueMeal.model.FoodPost;
import com.rescueMeal.model.User;
import com.rescueMeal.service.FoodPostService;
import org.hibernate.exception.ConstraintViolationException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodPostServiceImpl implements FoodPostService {

    @Autowired
    private FoodPostDAO foodPostDAO;

    @Autowired
    private ModelMapper modelMapper;

    private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    @Override
    public long createPost(FoodPostDTO foodPostDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            GeometryFactory geometryFactory = new GeometryFactory();
            double latitude = foodPostDTO.getLatitude();
            double longitude = foodPostDTO.getLongitude();
            Point location = geometryFactory.createPoint(new Coordinate(longitude, latitude));
            FoodPost foodPost=modelMapper.map(foodPostDTO,FoodPost.class);
            foodPost.setPostTime(LocalDateTime.now());
            foodPost.setLocation(location);
            foodPost.setUser((User) authentication.getPrincipal());
            System.out.println(location.toString());
            foodPostDAO.save(foodPost);
            return foodPost.getId();
        } catch (ConstraintViolationException e) {
            throw new FoodPostNotCreatedException("Food Post was not created due to validation failure: " + e.getMessage());
        } catch (Exception e) {
            throw new FoodPostNotCreatedException("An error occurred while creating FoodPost: " + e.getMessage());
        }
    }
    public List<FoodPostResponseDTO> getNearbyFoodPostsForNGO(Point ngoLocation, double radiusInKm) {
        double distanceInMeters = radiusInKm * 1000;
        List<FoodPost> foodPost= foodPostDAO.findNearByFoodPostsForNGO(ngoLocation, distanceInMeters);
        List<FoodPostResponseDTO>foodPostResponseDTOS=foodPost.stream()
                .map(foodPost1 -> modelMapper.map(foodPost1,FoodPostResponseDTO.class))
                .collect(Collectors.toList());
        return foodPostResponseDTOS;

    }

    public List<FoodPostResponseDTO>getAllFoodPosted(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u= (User) authentication.getPrincipal();
        List<FoodPost> foodPosts=foodPostDAO.findByUserId(u.getId());
        List<FoodPostResponseDTO>response=foodPosts.stream().map(foodPost -> modelMapper.map(foodPost,FoodPostResponseDTO.class))
                .collect(Collectors.toList());

        return response;
    }

    @Override
    public FoodPostResponseDTO checkTheStatus(Long id) {
        FoodPost foodPost = foodPostDAO.findById(id)
                .orElseThrow(() -> new FoodPostNotFound("FoodPost not found with the id: " + id));
        FoodPostResponseDTO foodPostResponseDTO=modelMapper.map(foodPost,FoodPostResponseDTO.class);
        return foodPostResponseDTO;
    }
}
