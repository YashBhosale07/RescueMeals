package com.rescueMeal.controller;

import com.rescueMeal.dto.NgoDTO;
import com.rescueMeal.model.FoodPost;
import com.rescueMeal.model.NGO;
import com.rescueMeal.service.FoodPostService;
import com.rescueMeal.service.NGOService;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/NGO")
public class NGOController {

    @Autowired
    private NGOService ngoService;

    @Autowired
    private FoodPostService foodPostService;

    @PostMapping("/register")
    public ResponseEntity<?>createNGO(@RequestBody NgoDTO ngo){
        Long id=ngoService.registerNGO(ngo);
        return new ResponseEntity<>("NGO registered with id: "+id, HttpStatus.CREATED);
    }

    public ResponseEntity<?>getAllNGO(){
        return null;
    }


    @GetMapping("/nearbyFoodPosts")
    public ResponseEntity<?> getNearbyFoodPosts(
            @RequestParam Long ngoId,
            @RequestParam double radiusInKm) {
        NGO ngo = ngoService.findById(ngoId);
        Point ngoLocation = ngo.getLocation();
        List<FoodPost> nearbyPosts = foodPostService.getNearbyFoodPostsForNGO(ngoLocation, radiusInKm);

        return ResponseEntity.ok(nearbyPosts);
    }
}
