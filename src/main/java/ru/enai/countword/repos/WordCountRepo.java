package ru.enai.countword.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.enai.countword.model.Word;

@EnableJpaRepositories
public interface WordCountRepo extends JpaRepository<Word, Long> {
}
