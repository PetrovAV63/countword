package ru.enai.countword.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.enai.countword.service.interfaces.SaveService;


import java.io.File;
import java.io.IOException;


@Service
@Qualifier("parserHtml")
public class ParserHtml implements SaveService {

    @Override
    public <T> String saveService(T t) {
        String filePath = (String) t;
        Document document = null;
        try {
            document = Jsoup.parse(new File(filePath), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return document.body().text();
    }
}
