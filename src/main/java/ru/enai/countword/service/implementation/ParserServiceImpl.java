package ru.enai.countword.service.implementation;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import ru.enai.countword.service.interfaces.ParserService;

import java.io.IOException;


@Service
public class ParserServiceImpl implements ParserService {
    private Document document;

    @Override
    public String parserHtml(String filepath) {
        try {
            document = Jsoup.connect(filepath).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document.body().text();
    }
}
