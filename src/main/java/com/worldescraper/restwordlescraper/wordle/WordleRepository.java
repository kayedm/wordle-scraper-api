package com.worldescraper.restwordlescraper.wordle;

import org.springframework.data.jpa.repository.JpaRepository;

interface WordleRepository extends JpaRepository<Wordle, Integer> {

}
