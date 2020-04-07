package duke.main;

import duke.constants.Constants;

import java.util.Scanner;

public class UiHandler {

    private String response;
    private boolean isExit;
    Scanner sc = new Scanner(System.in);

    public UiHandler() {
        isExit = false;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void exitDuke() {
        isExit = true;
    }

    public void welcomeMessage() {
        System.out.println(Constants.ANSI_RED + "Hello! I'm Duke\n" + "What's up?" + Constants.ANSI_RESET);
    }

    public String requestInput() {
        String request = sc.nextLine();
        return request;
    }

    public String getResponse() {
        return response;
    }

    public void displayResponse() {
        System.out.println(Constants.ANSI_RED + this.response + Constants.ANSI_RESET);
    }

    public boolean isExit() {
        return isExit;
    }
}
