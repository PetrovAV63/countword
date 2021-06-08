package ru.enai.countword.service.implementation;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SaverHtmlPageImpl.class)
class SaverHtmlPageImplTest {

    @Autowired
    SaverHtmlPageImpl saverHtmlPageImpl;

    @Test
    void save() {
        String link = saverHtmlPageImpl.save("https://www.simbirsoft.com");
        Assert.assertNotNull(link);
        Assertions.assertThat(link.contains(".html"));
    }


}