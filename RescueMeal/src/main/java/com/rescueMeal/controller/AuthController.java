package com.rescueMeal.controller;

import com.rescueMeal.dto.LoginDTO;
import com.rescueMeal.dto.SignUpDTO;
import com.rescueMeal.dto.UserDTO;
import com.rescueMeal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO=authService.signUp(signUpDTO);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/loginUp")
    public ResponseEntity<?>loginUp(@RequestBody LoginDTO loginDTO){
        String token=authService.login(loginDTO);
        return new ResponseEntity<String>(token,HttpStatus.FOUND);
    }

}
