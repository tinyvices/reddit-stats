package io.tinyvices.redditstats;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThreadRepository extends CrudRepository<Thread, Long> {

        List<Thread> findByTitle(String title);
}
