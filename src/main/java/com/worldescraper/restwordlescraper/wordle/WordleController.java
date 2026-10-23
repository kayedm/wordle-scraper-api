package com.worldescraper.restwordlescraper.wordle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wordles")
public class WordleController {

    private final WordleServiceImpl service;

    WordleController(WordleServiceImpl service) {
        this.service = service;

    }

    @GetMapping()
    List<Wordle> all() {
        return service.getAllWordles();
    }

    @GetMapping("/{id}")
    Wordle findById(@PathVariable int id) {
        return service.getById(id);
    }

}
