package ru.enai.countword.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.enai.countword.model.Word;
import ru.enai.countword.repos.WordCountRepo;
import ru.enai.countword.service.interfaces.SaveService;

import java.util.Map;

@Service("saveServiceDb")
public class SaveResultInDb implements SaveService {
    private WordCountRepo wordCountRepo;

    SaveResultInDb(WordCountRepo wordCountRepo) {
        this.wordCountRepo = wordCountRepo;
    }


    @Override
    public <T> String saveService(T t) {
        Map<String, Integer> mapResult = (Map<String, Integer>) t;
        for (Map.Entry<String, Integer> entry : mapResult.entrySet()){
            Word wordCount = Word.builder()
                    .word(entry.getKey())
                    .count(entry.getValue())
                    .build();
            wordCountRepo.save(wordCount);
        }
        return "Save to the data base";
    }
}
