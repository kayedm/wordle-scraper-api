package com.worldescraper.restwordlescraper.wordle;

import com.worldescraper.restwordlescraper.scraper.Scraper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

@Service
public class WordleService {

    private final WordleRepository repository;

    WordleService(WordleRepository repository) {
        this.repository = repository;
    }

    List<Wordle> getAllWordles() {
        return repository.findAll();
    }

    Wordle getById(int id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Worlde not found"));
    }

    Wordle addNewWordle(Wordle wordle) {
        return repository.save(wordle);
    }

    void scrapeData() throws IOException {
        Scraper scraper = new Scraper();
        scraper.fetchHtml();
        scraper.parseText();
        repository.saveAll(scraper.returnList());
    }

}
