package com.rescueMeal.controller;

import com.rescueMeal.dto.FoodPostDTO;
import com.rescueMeal.service.FoodPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodPost")
public class FoodPostController {

    @Autowired
    private FoodPostService foodPostService;

    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody FoodPostDTO post){
        long id=foodPostService.createPost(post);
        return new ResponseEntity<String>("Request has been raised with id: "+id, HttpStatus.CREATED);
    }

    public ResponseEntity<?>getAllFoodPost(){
        return null;
    }

}
