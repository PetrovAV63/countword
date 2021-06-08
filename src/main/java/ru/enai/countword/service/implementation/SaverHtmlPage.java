package ru.enai.countword.service.implementation;


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
public class SaverHtmlPage implements SaveService {
    @Value("${pages.path}")
    private String pagesPath;


    private URL url = null;
    private BufferedReader reader = null;
    private PrintWriter outputFile = null;


    @Override
    public String save(String link) {

        String fileName = link.substring(link.indexOf('.') + 1, link.lastIndexOf('.'));
        Path path = Paths.get(this.pagesPath);
        File destinationFile = null;

        try {
            Path directories = Files.createDirectories(path);
            destinationFile = new File(directories + "//" + fileName + ".html");
            outputFile = new PrintWriter(destinationFile);
            url = new URL(link);

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
            System.out.println("Error");
            return "Error";

        } catch (IOException e) {
            e.printStackTrace();
            return "Error";

        } finally {
            outputFile.close();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return destinationFile.getAbsolutePath();
    }
}