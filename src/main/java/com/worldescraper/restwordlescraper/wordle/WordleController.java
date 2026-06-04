package com.worldescraper.restwordlescraper.wordle;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wordles")
public class WordleController {

    private final WordleService service;

    WordleController(WordleService service) {
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
