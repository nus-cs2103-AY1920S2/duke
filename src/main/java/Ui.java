//package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private String userName;
    private BufferedReader br;

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "____________________________________________________________\n";
    private static final String GREETING = "I'm Duke.\n What can I do for you?\n";

    public Ui() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public void printCommandResult(String result) {
        System.out.println(LINE + result + LINE);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(LINE + errorMessage + LINE);
    }

    public void askForName() {
        this.printCommandResult("Please input user name.\n");
        String userName = this.getUserInput();
        this.setName(userName);
    }

    public void greet() {
        this.printCommandResult("Hello " + this.userName + "! " + GREETING);
    }

    public String getUserInput() {
        try {
            return this.br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
