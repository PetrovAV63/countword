package ru.enai.countword.service.interfaces;

import java.util.Map;

public interface WordCounterService {
    Map<String, Integer> countWord(String text, String link);
}
