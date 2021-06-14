package ru.enai.countword.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import ru.enai.countword.service.interfaces.ParserHtmlFromFileService;


import java.io.File;
import java.io.IOException;


@Service
public class ParserHtml implements ParserHtmlFromFileService {

    @Override
    public String parserHtmlFromFile(String link) {
        Document document = null;
        try {
            document = Jsoup.parse(new File(link), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(200);
        }
        return document.body().text();
    }
}
