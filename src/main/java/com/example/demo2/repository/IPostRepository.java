package com.example.demo2.repository;

import com.example.demo2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByStatusContainingIgnoreCase(String status);
    List<Post> findAllByOrderByLikesDesc();
    List<Post> findAllByOrderByLikesAsc();
    List<Post> findTop4ByOrderByLikesDesc();
}
