package ru.enai.countword.service.interfaces;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

public interface SaveResultService {
    File createdFile(String pathDirectories, String suffix);

    void saveInFile(Path path, Map<String, Integer> stringIntegerMap);

    void saveInDataBase();
}
