package com.example.roleBased.controller;

import com.example.roleBased.dto.LoginDto;
import com.example.roleBased.dto.Response;
import com.example.roleBased.entity.JwtRequest;
import com.example.roleBased.entity.JwtResponse;
import com.example.roleBased.entity.User;
import com.example.roleBased.serviceImpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    //create token for request and authenticate
    @PostMapping("/authenticateAdmin")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        return jwtService.createJwtToken(jwtRequest);
    }
}
