package io.tinyvices.redditstats;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ScheduleRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ScheduleRepository schedules;

    @Test
    public void testFindByTitle() {
        Schedule schedule = new Schedule();
        schedule.setTitle("testTitle");
        entityManager.persist(schedule);

        List<Schedule> findByTitle = schedules.findByTitle(schedule.getTitle());

        assertThat(findByTitle).extracting(Schedule::getTitle).containsOnly(schedule.getTitle());
    }
    @Test
    public void testFindBySubreddit() {
        Schedule schedule = new Schedule();
        schedule.setTitle("testTitle");
        schedule.setSubreddit("testSubreddit");
        entityManager.persist(schedule);

        List<Schedule> findBySubreddit = schedules.findBySubreddit(schedule.getSubreddit());

        assertThat(findBySubreddit).extracting(Schedule::getSubreddit).containsOnly(schedule.getSubreddit());
    }
}
