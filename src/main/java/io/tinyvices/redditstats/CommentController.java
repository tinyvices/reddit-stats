package io.tinyvices.redditstats;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private final CommentRepository repository;

    CommentController( CommentRepository repository ) {
        this.repository = repository;
    }

    @GetMapping("/comments")
    Iterable<Comment> all(
            @RequestParam(value = "redditId") String redditId
    ) {
        if (redditId != null ) return this.repository.findByRedditId(redditId);
        return this.repository.findAll();
    }

    @PostMapping("/comments")
    Comment newComment(@RequestBody Comment newComment) {
        return repository.save(newComment);
    }

    @GetMapping("/comments/{id}")
    Comment one(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {

        return repository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @PutMapping("/comments/{id}")
    Comment replaceComment(@RequestBody Comment newComment, @PathVariable Long id) {

        return repository.findById(id)
                .map(comment -> {
                    //comment.setTitle(newComment.getTitle());
                    return repository.save(comment);
                })
                .orElseGet(() -> {
                    newComment.setId(id);
                    return repository.save(newComment);
                });
    }

    @DeleteMapping("/comments/{id}")
    void deleteComment(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
