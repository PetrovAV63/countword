package ru.enai.countword.service;

import org.springframework.stereotype.Component;


@Component
public class AppManager {

    public void printMenu() {
        final String MENU = "Entry link and command." + "\n" +
                "Example: www.simbirsoft.ru -d -f -s" + "\n" +
                "Where: -d Save in database" + "\n" +
                "       -f Save in file" + "\n" +
                "       -s View on screen only";
        System.out.println(MENU);
    }

    public void start(String command) {
        String[] data = command.split("\\s");

        }
    }

