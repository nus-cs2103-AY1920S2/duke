package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    protected String userName;
    protected BufferedReader br;

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String line = "____________________________________________________________\n";
    static String greeting = "I'm Duke.\n What can I do for you?\n";

    public Ui() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public void printCommandResult(String result) {
        System.out.println(line + result+ line);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(line + errorMessage + line);
    }

    public void askForName() {
        this.printCommandResult("Please input user name.\n");
        String userName = this.getUserInput();
        this.setName(userName);
    }

    public void greet() {
        this.printCommandResult("Hello "+ this.userName + "! " + greeting);
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
