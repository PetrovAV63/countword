package ru.enai.countword.service.interfaces;

import java.util.Map;

public interface WordsCounterService {
    Map<String, Integer> countAndSave(String text, String link);
}
