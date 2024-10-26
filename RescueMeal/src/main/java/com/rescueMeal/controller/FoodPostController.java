package com.rescueMeal.controller;

import com.rescueMeal.dto.FoodPostDTO;
import com.rescueMeal.dto.FoodPostResponseDTO;
import com.rescueMeal.model.FoodPost;
import com.rescueMeal.service.FoodPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/checkStatus/{id}")
    public ResponseEntity<?>checkStatus(@PathVariable Long id ){
        FoodPostResponseDTO foodPost=foodPostService.checkTheStatus(id);
        return new ResponseEntity<>(foodPost,HttpStatus.OK);
    }

    @GetMapping("/getAllFoodPost")
    public ResponseEntity<?>getAllFoodPost(){
        List<FoodPostResponseDTO>foodPosts=foodPostService.getAllFoodPosted();
        return new ResponseEntity<>(foodPosts,HttpStatus.FOUND);
    }



}
