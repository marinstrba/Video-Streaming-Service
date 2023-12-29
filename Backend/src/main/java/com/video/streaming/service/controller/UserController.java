package com.video.streaming.service.controller;

import com.video.streaming.service.DTO.VideoDTO;
import com.video.streaming.service.service.UserRegistrationService;
import com.video.streaming.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRegistrationService userRegistrationService;
    private final UserService userService;
    @GetMapping(value = "/register")
    @ResponseStatus(HttpStatus.OK)
    public String register(Authentication authentication){
        Jwt jwt = (Jwt)authentication.getPrincipal();
        return userRegistrationService.registerUser(jwt.getTokenValue());
    }

    @PostMapping(value = "/subscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean subscribeUser(@PathVariable String userId){
        userService.subscribeUser(userId);
        return true;
    }

    @PostMapping(value = "/unsubscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean unSubscribeUser(@PathVariable String userId){
        userService.unSubscribeUser(userId);
        return true;
    }

    @GetMapping(value = "/{userId}/history")
    @ResponseStatus(HttpStatus.OK)
    public Set<String> userHistory(@PathVariable String userId){
        return userService.userHistory(userId);
    }

}
