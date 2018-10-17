package io.tinyvices.redditstats;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

        List<Schedule> findByTitle(String title);
        List<Schedule> findBySubreddit(String subreddit);
}
