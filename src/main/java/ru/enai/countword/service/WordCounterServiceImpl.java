package ru.enai.countword.service;

import org.springframework.stereotype.Service;
import ru.enai.countword.service.interfaces.WordCounterService;
import ru.enai.countword.utilits.Counter;
import ru.enai.countword.utilits.Splitter;

import java.util.Map;

@Service
public class WordCounterServiceImpl implements WordCounterService {
    private final Counter counter;
    private final Splitter splitter;

    public WordCounterServiceImpl(Counter counter, Splitter splitter) {
        this.counter = counter;
        this.splitter = splitter;
    }


    @Override
    public Map<String, Integer> countWord(String text, String link) {
        String[] words = splitter.splitWords(text);
        Map<String, Integer> result = counter.countWords(words);
        result.forEach((key, value) -> System.out.println(String.format("%s - %d", key, value)));
        return result;
    }
}
