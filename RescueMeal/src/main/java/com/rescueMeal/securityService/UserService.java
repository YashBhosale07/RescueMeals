package com.rescueMeal.securityService;

import com.rescueMeal.dao.UserDAO;
import com.rescueMeal.dto.SignUpDTO;
import com.rescueMeal.dto.UserDTO;
import com.rescueMeal.exceptionClasses.UserAlreadyPresentException;
import com.rescueMeal.exceptionClasses.UserNotFoundException;
import com.rescueMeal.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByEmail(username)
                .orElseThrow(()->new UserNotFoundException("User not found with email "+username
                +" please create a account" ));
    }

    public UserDTO signUp(@RequestBody SignUpDTO signUpDTO){
        Optional<User>user =userDAO.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()){
            throw new UserAlreadyPresentException("User is already present with email "+signUpDTO.getEmail());
        }
        User userToBeCreated=modelMapper.map(signUpDTO,User.class);
        userToBeCreated.setPassword(passwordEncoder.encode(userToBeCreated.getPassword()));
        User savedUser =userDAO.save(userToBeCreated);
        return modelMapper.map(savedUser,UserDTO.class);
    }
}
