package io.tinyvices.redditstats;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.in;

@RestController
public class ScheduleController {

    private final ScheduleRepository repository;

    ScheduleController(ScheduleRepository repository ) {
        this.repository = repository;
    }

    @GetMapping("/schedules")
    Iterable<Schedule> all() {
        return this.repository.findAll();
    }

    @PostMapping("/schedules")
    Schedule newSchedule(@RequestBody Schedule newSchedule) {

        List<Schedule> schedules = new ArrayList<>();
        repository.findAll().forEach(schedules::add);

        return repository.save(newSchedule);
    }

    @GetMapping("/schedules/{id}")
    Schedule one(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {

        return repository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @PutMapping("/schedules/{id}")
    Schedule replaceSchedule(@RequestBody Schedule newSchedule, @PathVariable Long id) {

        return repository.findById(id)
                .map(schedule -> {
                    //schedule.setTitle(newSchedule.getTitle());
                    return repository.save(schedule);
                })
                .orElseGet(() -> {
                    newSchedule.setId(id);
                    return repository.save(newSchedule);
                });
    }

    @DeleteMapping("/schedules/{id}")
    void deleteSchedule(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
