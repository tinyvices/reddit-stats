package io.tinyvices.redditstats;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ThreadRepository threads, CommentRepository comments) {
        return args -> {
//            log.info("Preloading " + threads.save(new Thread(new RedditId("asd"))));
//            log.info("Preloading " + threads.save(new Thread(new RedditId("asdasdaw"))));
//            log.info("Preloading " + comments.save(new Comment(new RedditId("asf324fd"), new RedditUser("tinyvices"), new RedditId("asdd"))));
//            log.info("Preloading " + comments.save(new Comment(new RedditId("asdaa4"), new RedditUser("tinyviceawaews"), new RedditId("aserd"))));
        };
    }
}
