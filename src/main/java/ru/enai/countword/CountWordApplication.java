package ru.enai.countword;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.enai.countword.service.AppManager;


@SpringBootApplication
public class CountWordApplication implements CommandLineRunner {
    private final AppManager appManager;
    private Boolean status;


    public CountWordApplication(AppManager appManager) {
        this.appManager = appManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(CountWordApplication.class, args);
    }


    @Override
    public void run(String... args) {
        status = true;

        while (status) {
            appManager.printMenu();
        }
    }
        public void setStatus(Boolean status) {
            this.status = status;
        }
    }
