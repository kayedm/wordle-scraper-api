package com.worldescraper.restwordlescraper.wordle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadData {

    private static final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Bean
    CommandLineRunner initData(WordleService service) {
        return args -> {
            try {
                service.scrapeData();
                log.info("Data loaded");
            } catch (Exception e) {
                log.error(String.valueOf(e));
            }
        };
    }
}
