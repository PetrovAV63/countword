package ru.enai.countword.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.enai.countword.CountWordApplication;
import ru.enai.countword.service.interfaces.SaveService;
import ru.enai.countword.service.interfaces.WordCounterService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


@Component
public class AppManager {

    private final SaveService saveServiceParser, saveServiceHtml, saveServiceDb;

    private final WordCounterService wordCounterService;

    private BufferedReader reader;

    public AppManager(@Qualifier("parserHtml") SaveService saveServiceParser,
                      @Qualifier("saveHtml") SaveService saveServiceHtml,
                      @Qualifier("saveServiceDb") SaveService saveServiceDb,
                      WordCounterService wordCounterService) {
        this.saveServiceParser = saveServiceParser;
        this.saveServiceHtml = saveServiceHtml;
        this.wordCounterService = wordCounterService;
        this.saveServiceDb = saveServiceDb;

    }


    public void printMenu() {
        System.out.println("Enter link" + "\n" +
                "Example: http://www.simbirsoft.com" + "\n" +
                " or q for exit");
        try {
            String link = new BufferedReader(new InputStreamReader(System.in)).readLine();
            if(!link.equals("q")) {
                start(link);
            } else {

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start(String link) {
        String pathToHtmlPage = saveServiceHtml.saveService(link);
        String text = saveServiceParser.saveService(pathToHtmlPage);//request parser here
        Map<String, Integer> resultMap = wordCounterService.countWord(text, link);
        questionSaveInDb(resultMap);
    }

    private void questionSaveInDb(Map<String, Integer> map) {
        System.out.println("Save the result to the database?" + "\n" +
                "Enter Y or N");

        try {
            String command = new BufferedReader(new InputStreamReader(System.in)).readLine();
            if (command.toUpperCase().equals("Y")) {
                saveServiceDb.saveService(map);
            } else {
                System.out.println("Application finish and ready for new command");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

