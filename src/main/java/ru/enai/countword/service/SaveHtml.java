package ru.enai.countword.service;



import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;


import ru.enai.countword.service.interfaces.SaveServiceHtml;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;


@Service
public class SaveHtml implements SaveServiceHtml {
    @Value("${pages.path}")
    private String path;



    @Override
    public String saveHtmlInFile(String link) {
        String fileName = link.substring(link.indexOf('/') + 1, link.lastIndexOf('.'));
        String pathFileHtml = "";
        try {
            Path newFilePath = createdFileToPath(fileName);
            String document = null;
            Set<String> singleton;

            Connection.Response response = Jsoup.connect(link).execute();

            if (response.statusCode() != 200) {
                throw new MalformedURLException();
            }

            pathFileHtml = newFilePath.toAbsolutePath().toString();
            document = Jsoup.connect(link).get().outerHtml();
            singleton = Collections.singleton(document);
            Files.write(newFilePath, singleton);

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathFileHtml;
    }

    private Path createdFileToPath(String fileName) {
        Path newFilePath = null;
        Path directories;
        try {
            directories = Files.createDirectories(Path.of(path));
            newFilePath = Paths.get(directories + "/" + fileName + ".html");
            if (!Files.exists(newFilePath)) {
                Files.createFile(newFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFilePath;
    }
}
