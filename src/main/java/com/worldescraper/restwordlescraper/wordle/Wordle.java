package com.worldescraper.restwordlescraper.wordle;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

/**
 * Data model for the wordle answer.
 * Stores the wordle number, answer and date.
 */

@Entity
@Table(name = "answers")
public class Wordle {

    @Id
    private int number;

    @Column(name = "word")
    @Size(min = 5 , max = 5, message = "Wordle answer must be 5 letters")
    private String answer;

    private LocalDate date;

    public Wordle() {
    }

    public Wordle(int number, String answer, LocalDate date) {
        this.number = number;
        this.answer = answer;
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Answer: " + answer + "\n" +
                "Date: " + date + "\n" +
                "Number: " + number + "\n" +
                "-".repeat(20) + "\n";
    }

}

