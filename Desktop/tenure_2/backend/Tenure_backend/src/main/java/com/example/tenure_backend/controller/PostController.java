package com.example.tenure_backend.controller;

import com.example.tenure_backend.dto.request.SavePostRequest;
import com.example.tenure_backend.dto.request.UpdatePostRequest;
import com.example.tenure_backend.dto.response.MypageResponse;
import com.example.tenure_backend.dto.response.PostResponse;
import com.example.tenure_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePost(@RequestBody @Valid SavePostRequest savePostRequest) {
        postService.savePost(savePostRequest);
    }

    @GetMapping
    public PostResponse getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public PostResponse.PostElement getPost(@PathVariable(value = "id") Long postId) {
        return postService.getPost(postId);
    }

    @PutMapping("/{id}")
    public Long updatePost(@PathVariable(value = "id") Long postId, @RequestBody @Valid UpdatePostRequest request) {
        return postService.updatePost(postId, request);
    }

    @GetMapping("/my")
    public MypageResponse getMyPost() {
        return postService.getMyPost();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable(value = "id") Long postId) {
        postService.deletePost(postId);
    }

}
