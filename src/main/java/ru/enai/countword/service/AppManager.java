package ru.enai.countword.service;

import org.springframework.stereotype.Component;


@Component
public class AppManager {


    public void printMenu() {
        System.out.println("Entry command" + "\n" + "Example: www.simbirsoft.ru or www.simbirsoft.ru -d " + "\n" +
                "(Where \"-d\" save to database)");
    }

    public void start(String command) {
        if(command.endsWith("-d")) {
            String linkAddress = command.split("\\s")[0];
            String suffixCommand = command.split("\\s")[1];
            
        }
    }
}
