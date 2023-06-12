package com.example.roleBased.controller;

import com.example.roleBased.config.JwtUtil;
import com.example.roleBased.dao.UserDao;
import com.example.roleBased.dto.AuthRequest;
import com.example.roleBased.dto.LoginResponse;
import com.example.roleBased.dto.Response;
import com.example.roleBased.dto.UserDto;
import com.example.roleBased.entity.User;
import com.example.roleBased.serviceImpl.JwtService;
import com.example.roleBased.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController
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
    public ResponseEntity<LoginResponse> generateToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginResponse.builder().message("not valid!").status(false).build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponse.builder().status(true).message("It is a Valid User!!").email(authRequest.getUserName()).jwtToken(JwtUtil.generateToken(authRequest.getUserName())).build());
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<LoginResponse> generateToken(@RequestBody AuthRequest authRequest) {
//        User user =null;
//        System.out.println(authRequest.getUserName());
//        System.out.println(authRequest.getPassword());
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getPassword(), authRequest.getPassword())
//            );
//            return ResponseEntity.status(LoginResponse.builder().status(true).message("Valide User!").)
//            user = userDao.findByEmail(authRequest.getUserName());
//            System.out.println(authRequest.getUserName());
//            if(user!=null){
//                String plaintextPassword = authRequest.getPassword();
//                String encryptedPassword = user.getPassword();
//
//                Boolean passwordMatched = passwordEncoder.matches(plaintextPassword,encryptedPassword);
//                System.out.println(passwordMatched);
//                if(passwordMatched) {
//                    Optional<User> validUser = userDao.findOneByEmailAndPassword(authRequest.getUserName(), encryptedPassword);
//                    if (user.isPresent()) {
//                        return ResponseEntity.status(HttpStatus.OK).body(LoginResponse.builder().message("It is a Valid User!!").status(true).email(authRequest.getUserName()).username(user.getUsername()).jwtToken(JwtUtil.generateToken(authRequest.getUserName())).build());
//                    }
//                    else{
//                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginResponse.builder().status(true).message("Invalid Credentials !!").build());
//                    }
//                }else {
//                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginResponse.builder().status(false).message("Credentials not Matching!!").build());
//                }
//            }
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(LoginResponse.builder().status(false).message("Email Not Exist !!").build());
//    }

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
