package com.worldescraper.restwordlescraper.wordle;

import java.io.IOException;
import java.util.List;

public interface WordleService {

    // Method to return all wordles
    List<Wordle> getAllWordles();

    // Method to get wordle by id
    Wordle getById(int id);

    // Method to create a list of wordles
    void scrapeData() throws IOException;

}
