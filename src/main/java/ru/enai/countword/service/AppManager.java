package ru.enai.countword.service;


import org.springframework.stereotype.Service;



@Service
public class AppManager {

    public void printMenu() {
        final String MENU = "Entry link and command." + "\n" +
                "Example: https://www.simbirsoft.com -d -f" + "\n" +
                "Where: -d Save in database" + "\n" +
                "       -f Save in file";
        System.out.println(MENU);
    }

    public void start(String command) {
        String[] data = command.split("\\s");

    }
}

