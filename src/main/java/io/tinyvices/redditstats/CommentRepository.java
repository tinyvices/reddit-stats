package io.tinyvices.redditstats;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

        List<Comment> findByAuthor(String author);

        List<Comment> findByRedditId(String redditId);
}
