package ru.enai.countword.service;



import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.enai.countword.service.interfaces.SaveServiceHtml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;


@Service()
public class SaveHtml implements SaveServiceHtml {
    @Value("${pages.path}")
    private String path;

    @Override
    public String saveHtmlInFile(String link) {
        String fileName = link.substring(link.indexOf('.') + 1, link.lastIndexOf('.'));
        String pathFileHtml = "";
        Path newFilePath;
        try {
            newFilePath = getPath(fileName);

            String document = null;
            Set<String> singleton;

            Connection.Response response = Jsoup.connect(link).execute();

            if (response.statusCode() != 200) {
                //TODO check response code
            }
            pathFileHtml = newFilePath.toAbsolutePath().toString();
            document = Jsoup.connect(link).get().outerHtml();
            singleton = Collections.singleton(document);
            Files.write(newFilePath, singleton);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathFileHtml;
    }

    private Path getPath(String fileName) throws IOException {
        Path newFilePath;
        Path directories;
        directories = Files.createDirectories(Path.of(path));
        newFilePath = Paths.get(directories + "/" + fileName + ".html");
        if (!Files.exists(newFilePath)) {
            Files.createFile(newFilePath);
        }
        return newFilePath;
    }
}
