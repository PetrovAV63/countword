package ru.enai.countword.utilits;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Counter {
    public Map<String, Integer> countWords(String[] words) {
        Map<String, Integer> countsWord = new LinkedHashMap<>();
        for (String word : words) {
            if (!countsWord.containsKey(word)) {
                countsWord.put(word, 1);
            } else
                countsWord.put(word, countsWord.get(word) + 1);
        }
        return countsWord;
    }
}
