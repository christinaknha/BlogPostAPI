package com.example.BlogPostAPI;

import com.example.BlogPostAPI.model.BlogPost;
import com.example.BlogPostAPI.repository.BlogPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//  Preloads database with a few entries
@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BlogPostRepository blogPostRepository) {

        return args -> {
            log.info("Preloading " + blogPostRepository.save(new BlogPost("Entry 1", "This is my first post", "hanhi")));
            log.info("Preloading " + blogPostRepository.save(new BlogPost("Entry 2","This is my second post", "hanhi")));
        };
    }
}
