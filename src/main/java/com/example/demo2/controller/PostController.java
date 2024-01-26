package com.example.demo2.controller;
import com.example.demo2.model.Post;
import com.example.demo2.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private IPostRepository postRepository;


    @GetMapping
    public ResponseEntity showListPost() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> postOptional = postRepository.findById(id);
        post.setId(postOptional.get().getId());
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postRepository.deleteById(id);
        return new ResponseEntity("xoa thanh cong", HttpStatus.OK);
    }

    @GetMapping("/public")
    public ResponseEntity<List<Post>> getPublicPosts() {
        List<Post> publicPosts = postRepository.findAllByStatusContainingIgnoreCase("public");
        return new ResponseEntity<>(publicPosts, HttpStatus.OK);
    }
    @GetMapping("/only-me")
    public ResponseEntity<List<Post>> getOnlyMePosts() {
        List<Post> onlyMePosts = postRepository.findAllByStatusContainingIgnoreCase("only me");
        return new ResponseEntity<>(onlyMePosts, HttpStatus.OK);
    }

    @GetMapping("/sort-like-up")
    public ResponseEntity<List<Post>> getSortLikeUp(){
        List<Post> list = postRepository.findAllByOrderByLikesDesc();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/sort-like-down")
    public ResponseEntity<List<Post>> getSortLikeDown(){
        List<Post> list = postRepository.findAllByOrderByLikesAsc();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/top4like")
    public ResponseEntity<List<Post>> getTop4Like(){
        List<Post> listTop4 = postRepository.findTop4ByOrderByLikesDesc();
        return new ResponseEntity<>(listTop4, HttpStatus.OK);
    }
}
