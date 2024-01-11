package com.example.tenure_backend.domain.repository;

import com.example.tenure_backend.domain.User;
import com.example.tenure_backend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();
    List<Post> findAllByUser(User user);

    @Query("select p from Post p order by p.date desc")
    List<Post> getPage();
}
