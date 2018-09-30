package io.tinyvices.redditstats;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository comments;

    @Test
    public void testFindByAuthor() {
        Comment comment = new Comment("asd");
        entityManager.persist(comment);

        List<Comment> findByAuthor = comments.findByAuthor(comment.getAuthor());

        assertThat(findByAuthor).extracting(Comment::getAuthor).containsOnly(comment.getAuthor());
    }
}
