package ru.enai.countword.service.implementation;

import org.springframework.stereotype.Service;
import ru.enai.countword.service.interfaces.SaveResultService;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class SaverService implements SaveResultService {
    @Override
    public File createdFile(String pathDirectories, String suffix) {
        String doubleBackSlash = "//";
        return new File(Paths.get(pathDirectories) + doubleBackSlash + suffix);
    }

    @Override
    public void saveInFile(Path path, Map<String, Integer> stringIntegerMap) {

    }

    @Override
    public void saveInDataBase() {

    }
}
