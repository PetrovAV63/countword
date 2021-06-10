package ru.enai.countword.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.enai.countword.service.interfaces.SaveService;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Qualifier("saveHtml")
public class SaveHtml implements SaveService {
    @Value("${pages.path}")
    private String path;

    @Override
    public <T> String saveService(T t) {
        String link = (String) t;

        String fileName = link.substring(link.indexOf('.') + 1, link.lastIndexOf('.'));
        Path path = Paths.get(this.path);
        File destinationFile = null;
        PrintWriter outputFile = null;
        BufferedReader reader = null;
        try{
            Path directories = Files.createDirectories(path);
            destinationFile = new File(directories + "//" + fileName + ".html");
            outputFile = new PrintWriter(destinationFile);
            URL url = new URL(link);
            URLConnection con = url.openConnection();
            InputStream inputStream = con.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            while (reader.ready()) {
                outputFile.println(reader.readLine());
            }

            reader.close();
            outputFile.close();

        } catch (FileNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outputFile != null)
                outputFile.close();
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return destinationFile.getAbsolutePath();
    }
}
