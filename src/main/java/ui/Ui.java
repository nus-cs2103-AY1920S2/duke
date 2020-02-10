package ui;

import java.util.Scanner;

/**
 * This class deals with interactions with the user.
 */
public class Ui {

    public static final String LINE = "____________________________________________________________";
    private Scanner sc;

    /**
     * Constructor for creating new Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }


    /**
     * Prints the message to be shown to user.
     */
    public void printMsg(String msg) {
        assert msg.length() != 0 : "Assert error: no message to print!";
        System.out.println(msg);
    }


}
