package io.tinyvices.redditstats;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedditStatsController {

    @RequestMapping("/")
    public String index() { return "TODO"; }
}
