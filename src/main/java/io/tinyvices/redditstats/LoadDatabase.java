package io.tinyvices.redditstats;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Configuration
@Slf4j
public class LoadDatabase {

    private ObjectMapper mapper = new ObjectMapper();

    @Bean
    CommandLineRunner initDatabase(ScheduleRepository schedules, ThreadRepository threads, CommentRepository comments) throws IOException {
        String jsonIn = new String(Files.readAllBytes(Paths.get("src/test/resources/automoderatorScheduleList.json")));

        List<Schedule> loadSchedules = mapper.readValue(jsonIn, new TypeReference<List<Schedule>>() {});
        loadSchedules.forEach(s -> s.setSubreddit("financialindependence"));
        return args -> {
            log.info("Preloading schedules");
            schedules.saveAll(loadSchedules);
//            log.info("Preloading " + threads.save(new Thread(new RedditId("asd"))));
//            log.info("Preloading " + threads.save(new Thread(new RedditId("asdasdaw"))));
//            log.info("Preloading " + comments.save(new Comment(new RedditId("asf324fd"), new RedditUser("tinyvices"), new RedditId("asdd"))));
//            log.info("Preloading " + comments.save(new Comment(new RedditId("asdaa4"), new RedditUser("tinyviceawaews"), new RedditId("aserd"))));
        };
    }
}
