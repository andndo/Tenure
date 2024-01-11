package com.example.tenure_backend.controller;

import com.example.tenure_backend.dto.request.UserLoginRequest;
import com.example.tenure_backend.dto.request.UserSignupRequest;
import com.example.tenure_backend.dto.TokenDto;
import com.example.tenure_backend.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService memberService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        return memberService.signUp(userSignupRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDto login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        return memberService.login(userLoginRequest);
    }

}