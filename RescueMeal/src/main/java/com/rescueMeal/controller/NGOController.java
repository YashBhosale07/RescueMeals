package com.rescueMeal.controller;

import com.rescueMeal.dto.FoodPostResponseDTO;
import com.rescueMeal.dto.NgoDTO;
import com.rescueMeal.dto.NgoResponseDTO;
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
@RequestMapping("/ngo")
public class NGOController {

    @Autowired
    private NGOService ngoService;

    @Autowired
    private FoodPostService foodPostService;



    @GetMapping("/nearbyFoodPosts")
    public ResponseEntity<?> getNearbyFoodPosts(
            @RequestParam Long ngoId,
            @RequestParam double radiusInKm) {
        NGO ngo = ngoService.findById(ngoId);
        Point ngoLocation = ngo.getLocation();
        List<FoodPostResponseDTO> nearbyPosts = foodPostService.getNearbyFoodPostsForNGO(ngoLocation, radiusInKm);

        return ResponseEntity.ok(nearbyPosts);
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<?>acceptThePost(@PathVariable Long id){
        NgoResponseDTO ngoResponseDTO =ngoService.acceptThePost(id);
        return new ResponseEntity<NgoResponseDTO>(ngoResponseDTO,HttpStatus.ACCEPTED);
    }


}
