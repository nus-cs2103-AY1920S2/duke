package ui;

import java.util.Scanner;

public class Ui {

    public static final String LINE = "____________________________________________________________";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);

    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE + "\n Hello from\n" + logo + "\n How can I help you today? \n" + LINE);

    }

    public String readCommand() {
        return this.sc.nextLine();

    }

    public void showLoadingError() {
        System.out.println("----LOADING ERROR----");
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void printMsg(String msg) {
        System.out.println(msg);
    }


}
