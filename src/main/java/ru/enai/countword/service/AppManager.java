package ru.enai.countword.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.enai.countword.service.interfaces.SaveService;
import ru.enai.countword.service.interfaces.SaveServiceHtml;
import ru.enai.countword.service.interfaces.WordCounterService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


@Service
public class AppManager {

    private final SaveService saveServiceParser, saveServiceDb;
    private final SaveServiceHtml saveServiceHtml;



    private final WordCounterService wordCounterService;


    public AppManager(@Qualifier("parserHtml") SaveService saveServiceParser,
                      SaveServiceHtml saveServiceHtml,
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start(String link) {
        String pathToHtmlPage = saveServiceHtml.saveHtmlInFile(link);

        String text = saveServiceParser.saveService(pathToHtmlPage);//request parser here

        Map<String, Integer> resultMap = wordCounterService.countWord(text, link);

        //questionSaveInDb(resultMap);
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

