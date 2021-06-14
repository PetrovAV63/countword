package ru.enai.countword.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.enai.countword.service.interfaces.SaveResultService;
import ru.enai.countword.service.interfaces.SaveService;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class SaverHtmlPageImpl implements SaveService {
    @Value("${pages.path}")
    private String pagesPath;
    @Value("${pages.suffix}")
    private String suffix;

    private final SaveResultService saveResultService;

    private BufferedReader reader = null;
    private PrintWriter outputFile = null;

    public SaverHtmlPageImpl(SaveResultService saveResultService) {
        this.saveResultService = saveResultService;
    }


    @Override
    public String save(String link) {

        String fileName = link.substring(link.indexOf('.') + 1, link.lastIndexOf('.'));
        File destinationFile = null;

        try {
            destinationFile = saveResultService.createdFile(this.pagesPath, suffix);
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