package com.example.BlogPostAPI.service;


import com.example.BlogPostAPI.model.BlogPost;
import com.example.BlogPostAPI.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository){
        this.blogPostRepository = blogPostRepository;
    }

//    Method to return list of all blog post
    public List<BlogPost> getAllBlogPost(){
        return blogPostRepository.findAll();
    }

//    Method to create new blog post
    public BlogPost createNewPost(BlogPost blogPost){
        return blogPostRepository.save(blogPost);
    }

//    Method to return blog post with a specific id
    public Optional<BlogPost> getBlogPostById(Long id){
        return blogPostRepository.findById(id);
    }

//    Method to update blog post with specific id
    public Optional<BlogPost> updateBlogPostById(Long id, BlogPost updatedBlogPost){
        Optional<BlogPost> blogPostToUpdate = blogPostRepository.findById(id);
        if (blogPostToUpdate.isPresent()){
            BlogPost BlogPostExists = blogPostToUpdate.get();
            BlogPostExists.setTitle(updatedBlogPost.getTitle());
            BlogPostExists.setContent(updatedBlogPost.getContent());
            BlogPostExists.setAuthor(updatedBlogPost.getAuthor());
            return Optional.of(blogPostRepository.save(BlogPostExists));
        }
        return Optional.empty();
    }

//    Method to delete blog post with specified id
    public boolean deleteBlogPost(Long id){
        if(blogPostRepository.findById(id) != null){
            blogPostRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
