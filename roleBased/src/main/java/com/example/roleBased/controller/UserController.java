package com.example.roleBased.controller;

import com.example.roleBased.config.JwtUtil;
import com.example.roleBased.dao.UserDao;
import com.example.roleBased.entity.JwtRequest;
import com.example.roleBased.entity.JwtResponse;
import com.example.roleBased.entity.User;
import com.example.roleBased.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    //add user by email and login user
    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> createJwtToken(@RequestBody JwtRequest jwtRequest) {
        User user = userDao.findByEmail(jwtRequest.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword())
            );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(JwtResponse.builder().message("not valid!").status(false).build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(JwtResponse.builder().status(true).message("It is a Valid User!!").username(user.getUsername()).roles(user.getRoles()).userId(user.getUserId()).email(jwtRequest.getEmail()).jwtToken(JwtUtil.generateToken(jwtRequest.getEmail())).build());
    }

    @GetMapping("/userInfo/{userId}")
    public User getUserInfo(@PathVariable Integer userId){
        return userService.getUsername(userId);
    }

    //add or register new user
    @PostMapping("/registerNewUser")
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);
    }

    //method to initialize the roles of admin and user
    @PostConstruct
    public void initRolesAndUsers(){
         userService.initRolesAndUsers();
    }

   //redirect to admin home page
    @GetMapping("/forAdmin")
    public String forAdmin(){
        return "Only accessible for admin";
    }

    //redirect to user home page
    @GetMapping("/forUser")
    public String forUser(){
        return "Only accessible for user";
    }

}
