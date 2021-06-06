package ru.enai.countword.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.enai.countword.model.Word;

public interface WordCountRepo extends JpaRepository<Word, Long> {
}
