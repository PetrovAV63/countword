package ru.enai.countword.service.implementation;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.enai.countword.service.SaveHtml;
import ru.enai.countword.service.interfaces.SaveServiceHtml;


@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SaveHtml.class)
class SaverHtmlPageImplTest {

    @Autowired
    SaveServiceHtml saveServiceHtml;

    @Test
    void save() {
        String link = saveServiceHtml.saveHtmlInFile("https://www.simbirsoft.com");
        org.junit.jupiter.api.Assertions.assertNotNull(link);
        Assertions.assertThat(link.contains(".html"));
    }


}