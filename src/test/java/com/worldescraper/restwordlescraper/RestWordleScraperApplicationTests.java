package com.worldescraper.restwordlescraper;

import com.worldescraper.restwordlescraper.scraper.Scraper;
import com.worldescraper.restwordlescraper.wordle.WordleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RestWordleScraperApplicationTests {

	Scraper scraper = new Scraper();

	@Autowired
	WordleServiceImpl service;

	@Test
	void contextLoads() {
	}

	@Test
	void getWordleById() {
		assertDoesNotThrow(() -> {
			service.getById(1);
		});
	}

	@Test
	void getAllWordles() {
		assertThat(service.getAllWordles()).isNotEmpty();
	}

	@Test
	void testScraper() {
		try {
			scraper.fetchHtml();
			scraper.parseText();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertThat(scraper.returnList()).isNotEmpty();
		System.out.print(scraper.returnList());
	}

}
