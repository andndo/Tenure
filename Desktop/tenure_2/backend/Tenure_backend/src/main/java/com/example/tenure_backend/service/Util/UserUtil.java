package com.example.tenure_backend.service.Util;

import com.example.tenure_backend.domain.User;
import com.example.tenure_backend.domain.repository.UserRepository;
import com.example.tenure_backend.exception.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository memberRepository;

    public String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException();
        }
        return authentication.getName();
    }

    public User getUser() {
        return memberRepository.findById(getUserId()).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}
