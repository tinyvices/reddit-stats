package io.tinyvices.redditstats;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThreadController {

    private final ThreadRepository repository;

    ThreadController( ThreadRepository repository ) {
        this.repository = repository;
    }

    @GetMapping("/threads")
    Iterable<Thread> all() {
        return this.repository.findAll();
    }

    @PostMapping("/threads")
    Thread newThread(@RequestBody Thread newThread) {
        return repository.save(newThread);
    }

    @GetMapping("/threads/{id}")
    Thread one(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {

        return repository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @PutMapping("/threads/{id}")
    Thread replaceThread(@RequestBody Thread newThread, @PathVariable Long id) {

        return repository.findById(id)
                .map(thread -> {
                    //thread.setTitle(newThread.getTitle());
                    return repository.save(thread);
                })
                .orElseGet(() -> {
                    newThread.setId(id);
                    return repository.save(newThread);
                });
    }

    @DeleteMapping("/threads/{id}")
    void deleteThread(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
