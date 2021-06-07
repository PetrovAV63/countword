package ru.enai.countword;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.enai.countword.service.AppManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@SpringBootApplication
public class CountWordApplication implements CommandLineRunner {
    final AppManager appManager;


    public CountWordApplication(AppManager appManager) {
        this.appManager = appManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(CountWordApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String command = "";
        appManager.printMenu();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            command = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        appManager.start(command);
    }
}