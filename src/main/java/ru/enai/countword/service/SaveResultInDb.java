package ru.enai.countword.service;

import org.springframework.stereotype.Service;
import ru.enai.countword.model.Word;
import ru.enai.countword.repos.WordCountRepo;
import ru.enai.countword.service.interfaces.SaveServiceDataBase;

import java.util.Map;

@Service
public class SaveResultInDb implements SaveServiceDataBase {
    private final WordCountRepo wordCountRepo;

    SaveResultInDb(WordCountRepo wordCountRepo) {
        this.wordCountRepo = wordCountRepo;
    }

    @Override
    public void saveResultToDataBase(Map<String, Integer> result) {
        for (Map.Entry<String, Integer> entry : result.entrySet()){
            Word wordCount = Word.builder()
                    .word(entry.getKey())
                    .counts(entry.getValue())
                    .build();
            wordCountRepo.save(wordCount);
        }
        System.out.println("Save result in data base");
    }
}
