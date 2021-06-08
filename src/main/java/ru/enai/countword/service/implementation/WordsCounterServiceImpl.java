package ru.enai.countword.service.implementation;

import org.springframework.stereotype.Service;
import ru.enai.countword.service.interfaces.WordsCounterService;
import ru.enai.countword.utilits.Counter;
import ru.enai.countword.utilits.SplitterWord;

import java.util.Map;

@Service
public class WordsCounterServiceImpl implements WordsCounterService {
    private final SplitterWord splitterWord;
    private final Counter counter;

    public WordsCounterServiceImpl(SplitterWord splitterWord, Counter counter) {
        this.splitterWord = splitterWord;
        this.counter = counter;
    }

    @Override
    public Map<String, Integer> countAndSave(String text, String link) {
        String[] wordsArr = splitterWord.splitWords(text);
        Map<String, Integer> wordCount = counter.countWords(wordsArr);
        wordCount.forEach((key, value) -> System.out.println(key + " count is " + value));
        return wordCount;
    }
}
