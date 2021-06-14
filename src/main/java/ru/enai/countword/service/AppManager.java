package ru.enai.countword.service;

import org.springframework.stereotype.Service;

import ru.enai.countword.model.Word;
import ru.enai.countword.repos.WordCountRepo;
import ru.enai.countword.service.interfaces.ParserHtmlFromFileService;
import ru.enai.countword.service.interfaces.SaveServiceDataBase;
import ru.enai.countword.service.interfaces.SaveServiceHtml;
import ru.enai.countword.service.interfaces.WordCounterService;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@Service
public class AppManager {
    private final ParserHtmlFromFileService parserHtml;
    private final SaveServiceHtml saveServiceHtml;
    private final WordCounterService wordCounterService;
    private final SaveServiceDataBase saveServiceDataBase;
    private final WordCountRepo repo;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public AppManager(ParserHtmlFromFileService parserHtml,
                      SaveServiceHtml saveServiceHtml,
                      WordCounterService wordCounterService,
                      SaveServiceDataBase saveServiceDataBase,
                      WordCountRepo repo) {
        this.parserHtml = parserHtml;
        this.saveServiceHtml = saveServiceHtml;
        this.wordCounterService = wordCounterService;
        this.saveServiceDataBase = saveServiceDataBase;
        this.repo = repo;
    }



    public void start() {
        try {
            getMenu();
            String link = reader.readLine();

            String pathToHtmlPage = saveServiceHtml.saveHtmlInFile(link);
            String text = parserHtml.parserHtmlFromFile(pathToHtmlPage);
            Map<String, Integer> resultMap = wordCounterService.countWord(text, link);

            saveResultToDataBaseQuestion();
            String saveDateBase = reader.readLine();
            if (saveDateBase.equals("Y")) {
                saveServiceDataBase.saveResultToDataBase(resultMap);
            }
            viewNextAction();
            String action = reader.readLine();
            nextAction(action);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nextAction(String action) throws IOException {
        if (action.equals("-v")) {
            viewResult();
        } else if (action.equals("-q")) {
            reader.close();
            System.exit(100);
        }
    }


    private void getMenu() {
        System.out.println("Enter link" + "\n" +
                "Example: https://www.simbirsoft.com");
    }

    private void saveResultToDataBaseQuestion() {
        System.out.println("Do you save result to data base?" + "\n" +
                "Enter 'Y' or 'N'");
    }

    private void viewNextAction() {
        System.out.println("What do you next?" + "\n" +
                "'-q' - exit" + "\n" +
                "'-v' - view result" + "\n");
    }

    private void viewResult() {
        List<Word> all = repo.findAll();
        all.forEach(System.out::println);
    }
}

