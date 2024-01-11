package com.example.tenure_backend.service;

import com.example.tenure_backend.domain.repository.UserRepository;
import com.example.tenure_backend.domain.User;
import com.example.tenure_backend.dto.request.UserLoginRequest;
import com.example.tenure_backend.dto.request.UserSignupRequest;
import com.example.tenure_backend.dto.TokenDto;
import com.example.tenure_backend.exception.NotMatchesPasswordException;
import com.example.tenure_backend.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public String signUp(UserSignupRequest request) {
        userRepository.save(User.builder()
                .id(request.getUserId())
                .nickname(request.getNickname())
                .job(request.getJob())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());

        return "Success Signup";
    }

    public TokenDto login(UserLoginRequest request) {
        User member = userRepository.findById(request.getUserId()).get();

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw NotMatchesPasswordException.EXCEPTION;
        }

        return new TokenDto(tokenProvider.generateToken(member.getId()));
    }
}