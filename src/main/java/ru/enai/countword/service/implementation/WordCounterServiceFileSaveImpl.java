package ru.enai.countword.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.enai.countword.service.interfaces.WordsCounterService;

import java.util.Map;


@Service("-f")
public class WordCounterServiceFileSaveImpl implements WordsCounterService {
    @Value("${result.file.path}")
    private String directoryPath;
    @Value("${result.file.suffix}")
    private String suffix;


    private final WordsCounterServiceImpl wordsCounterService;

    public WordCounterServiceFileSaveImpl(WordsCounterServiceImpl wordsCounterService) {
        this.wordsCounterService = wordsCounterService;
    }


    @Override
    public Map<String, Integer> countAndSave(String text, String link) {
        Map<String, Integer> wordCount = wordsCounterService.countAndSave(text, link);


        return null;
    }
}
