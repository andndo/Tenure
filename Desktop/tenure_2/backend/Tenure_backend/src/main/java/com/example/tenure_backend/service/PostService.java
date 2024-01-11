package com.example.tenure_backend.service;

import com.example.tenure_backend.domain.User;
import com.example.tenure_backend.domain.Post;
import com.example.tenure_backend.domain.repository.PostRepository;
import com.example.tenure_backend.dto.request.SavePostRequest;
import com.example.tenure_backend.dto.request.UpdatePostRequest;
import com.example.tenure_backend.dto.response.MypageResponse;
import com.example.tenure_backend.dto.response.PostResponse;
import com.example.tenure_backend.exception.PostNotFoundException;
import com.example.tenure_backend.jwt.user.Details;
import com.example.tenure_backend.service.Util.UserUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Transactional
    public void savePost(SavePostRequest savePostRequest) {
        System.out.println(userUtil.getUser());
        UsernamePasswordAuthenticationToken contextInfo = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Details details = (Details) contextInfo.getPrincipal();
        User user = details.getUser();


        postRepository.save(
                Post.builder()
                        .location(savePostRequest.getLocation())
                        .title(savePostRequest.getTitle())
                        .content(savePostRequest.getContent())
                        .user(user)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public PostResponse getPosts() {
        List<PostResponse.PostElement> postElementList = postRepository.getPage()
                .stream().map(
                        post ->
                                PostResponse.PostElement.builder()
                                        .id(post.getId())
                                        .location(post.getLocation())
                                        .title(post.getTitle())
                                        .content(post.getContent())
                                        .date(post.getDate())
                                        .nickname(post.getUser().getNickname())
                                        .memberId(post.getUser().getId())
                                        .build()
                ).collect(Collectors.toList());

        return new PostResponse(postElementList);
    }

    @Transactional(readOnly = true)
    public MypageResponse getMyPost() {
        User user = userUtil.getUser();

        List<MypageResponse.PostElement> postElementList = postRepository.findAllByUser(user)
                .stream().map(
                        post ->
                                MypageResponse.PostElement.builder()
                                        .id(post.getId())
                                        .location(post.getLocation())
                                        .title(post.getTitle())
                                        .content(post.getContent())
                                        .date(post.getDate())
                                        .build()
                ).collect(Collectors.toList());

        return MypageResponse.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .job(user.getJob())
                .postElementList(postElementList)
                .build();
    }

    @Transactional(readOnly = true)
    public PostResponse.PostElement getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);

        return PostResponse.PostElement.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .location(post.getLocation())
                .date(post.getDate())
                .nickname(post.getUser().getNickname())
                .memberId(post.getUser().getId())
                .build();
    }

    @Transactional
    public Long updatePost(Long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return post.update(request.getTitle(), request.getContent());
    }

    @Transactional
    public void deletePost(Long postId) {
        Post postEntity = postRepository.findById(postId).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        postRepository.delete(postEntity);
    }

}
