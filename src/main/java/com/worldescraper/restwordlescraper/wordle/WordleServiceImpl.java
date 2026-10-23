package com.worldescraper.restwordlescraper.wordle;

import com.worldescraper.restwordlescraper.scraper.Scraper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WordleServiceImpl implements WordleService {

    private WordleRepository repository;

    WordleServiceImpl(WordleRepository repository) {
        this.repository = repository;
    }

    public List<Wordle> getAllWordles() {
        List<Wordle> list = repository.findAll();
        if(list.isEmpty()) {
            throw new WordleFindAllException("No Wordles found in database");
        }
        return list;
    }

    public Wordle getById(int id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Wordle not found"));
    }

    public void scrapeData() throws IOException {
        Scraper scraper = new Scraper();
        scraper.fetchHtml();
        scraper.parseText();
        repository.saveAll(scraper.returnList());
    }

}
