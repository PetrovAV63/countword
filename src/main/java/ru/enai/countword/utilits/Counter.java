package ru.enai.countword.utilits;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Counter {
    public Map<String, Integer> countWords(String[] words) {
        Map<String, Integer> counts = new LinkedHashMap<>();
        for(String word : words){
            if (!counts.containsKey(word)){
                counts.put(word, 1);
            }
            else
                counts.put(word, counts.get(word)+1);
        }
        return counts;
    }
}
