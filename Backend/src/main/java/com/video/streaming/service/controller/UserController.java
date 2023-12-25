package com.video.streaming.service.controller;

import com.video.streaming.service.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping(value="/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRegistrationService userRegistrationService;
    @GetMapping(value = "/register")
    public String register(Authentication authentication){
        Jwt jwt = (Jwt)authentication.getPrincipal();

        userRegistrationService.registerUser(jwt.getTokenValue());
        return "User registration was successful";
    }

}
