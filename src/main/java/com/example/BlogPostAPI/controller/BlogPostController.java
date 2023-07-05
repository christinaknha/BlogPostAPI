package com.example.BlogPostAPI.controller;

import com.example.BlogPostAPI.model.BlogPost;
import com.example.BlogPostAPI.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class BlogPostController {
    private final BlogPostService blogPostService;

//  BlogPostController constructor with BlogPostService injection
    @Autowired
    public BlogPostController(BlogPostService blogPostService){
        this.blogPostService = blogPostService;
    }


//  Gets all the blog post in the repository and displays them
    @GetMapping("/posts")
    public ResponseEntity<List<BlogPost>> getAllBlogPost(){
        List<BlogPost> blogPosts = blogPostService.getAllBlogPost();
        return ResponseEntity.ok(blogPosts);
    }

//  Creates a new blog post from a constructed blogpost that includes title, author, and content
    @PostMapping("/posts")
    public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost blogPost){
        BlogPost BlogPostToCreate = blogPostService.createNewPost(blogPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(BlogPostToCreate);
    }


//  Gets a blog post based on inputted id
    @GetMapping("/posts/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id){
        Optional<BlogPost> blogPost = blogPostService.getBlogPostById(id);
        return blogPost.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

//  Updates blog post based on inputted id
    @PutMapping("/posts/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id, @RequestBody BlogPost updatedBlogPost){
        Optional<BlogPost> blogPostToUpdate = blogPostService.updateBlogPostById(id, updatedBlogPost);
        return blogPostToUpdate.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

//  Deletes blog post based on inputted id
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id){
        boolean deleted = blogPostService.deleteBlogPost(id);
        if(deleted){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
