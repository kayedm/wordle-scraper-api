package com.worldescraper.restwordlescraper.scraper;

import com.worldescraper.restwordlescraper.wordle.Wordle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * This class fetches a html document and stores it as a file.
 * It creates and returns a list of wordle answers from the file.
 */
public class Scraper {

    private final String wordleUrl = "https://wordfinder.yourdictionary.com/wordle/answers/";
    private List<Wordle> answerList = new ArrayList<>();
    private List<String> cells = new ArrayList<>();

    /**
     * Fetches the html doc from a url and appends the current date.
     * If the current date doesn't match the html doc is re-fetched.
     * This is because wordle updates once a day so we update on new day.
     * @throws IOException If the document cannot be downloaded or written to the disk
     */
    public void fetchHtml() throws IOException {
        String date = LocalDate.now().toString();
        if (!Files.exists(Paths.get("doc.html"))) {

            Document doc = Jsoup.connect(wordleUrl)
                    .userAgent("Mozilla/5.0")
                    .get();

            try (FileWriter fw = new FileWriter("doc.html")) {
                fw.write(doc.html() + System.getProperty("line.separator"));
                fw.write(date);
            }
        } else {
            try(BufferedReader br = Files.newBufferedReader(Paths.get("doc.html")) ) {
                Queue<String> queue = new LinkedList<>();
                String line;
                while((line = br.readLine()) != null) {
                    if(queue.size() >= 1) {
                        queue.remove();
                    }
                    queue.add(line);
                }

                if(!queue.contains(date)) {
                    Files.deleteIfExists(Paths.get("doc.html"));
                    fetchHtml();
                }
            }
        }

    }



    /**
     * Reads the html file and extracts the table cells that store the wordle answers and creates a list out of the
     * extracted data.
     * @throws IOException If the file cannot be read
     */
    public void parseText() throws IOException {

        Document doc = Jsoup.parse(new File("doc.html"), "UTF-8");
        Elements tableRow = doc.select("td");

        // Remove unwanted words from the text
        for (int i = 0; i < tableRow.size(); i++) {
            String text = tableRow.get(i).text();

            text = text.replaceAll("Today", "").replaceAll("Reveal", "").trim();

            cells.add(text);
        }


        int yearCount = 0;

        for (int i = 0; i < cells.size(); i += 3) {

            // Convert string to date
            String dateStr = cells.get(i);
            int year = LocalDate.now().getYear();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM. dd yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(dateStr + " " + year, formatter);

            // The parsed data does not contain years so they must be manually added
            if (date.getMonth() == Month.DECEMBER && date.getDayOfMonth() == date.lengthOfMonth()) {
                yearCount++;
            }
            date = date.minusYears(yearCount);

            int number = Integer.parseInt(cells.get(i + 1));

            String word = cells.get(i + 2);

            answerList.add(new Wordle(number, word, date));
        }
    }

    /**
     * Returns a list of records containing wordle answers
     * @return the list of wordle answers
     */
    public List<Wordle> returnList() {
        return answerList;
    }

}