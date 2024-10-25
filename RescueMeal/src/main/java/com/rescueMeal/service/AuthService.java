package com.rescueMeal.service;

import com.rescueMeal.dao.UserDAO;
import com.rescueMeal.dto.LoginDTO;
import com.rescueMeal.dto.SignUpDTO;
import com.rescueMeal.dto.UserDTO;
import com.rescueMeal.exceptionClasses.UserAlreadyPresentException;
import com.rescueMeal.model.User;
import com.rescueMeal.utils.Roles;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public UserDTO signUp(SignUpDTO signUpDTO){
        Optional<User> user=userDAO.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()){
            throw new UserAlreadyPresentException("User is already present with this email: "
            +signUpDTO.getEmail());
        }
        User userToBeCreated=modelMapper.map(signUpDTO,User.class);
        userToBeCreated.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        userToBeCreated.setRole(Roles.valueOf( "ROLE_" +signUpDTO.getRole().toUpperCase()));
        User savedUser=userDAO.save(userToBeCreated);
        return modelMapper.map(savedUser,UserDTO.class);


    }

    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        return tokenService.tokenGenerate(user);
    }

}
