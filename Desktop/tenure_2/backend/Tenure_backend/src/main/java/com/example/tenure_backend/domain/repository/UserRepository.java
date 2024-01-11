package com.example.tenure_backend.domain.repository;

import com.example.tenure_backend.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findById(String id);
}
