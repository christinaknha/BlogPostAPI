package com.example.BlogPostAPI.repository;

import com.example.BlogPostAPI.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}
