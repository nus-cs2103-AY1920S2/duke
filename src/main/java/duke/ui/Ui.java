package duke.ui;

import java.util.Scanner;

public class Ui {

    public static void showLoadingError() {
        System.out.println("Loading error");
    }

    public static void showError(String message) {
        System.out.println(message);
    }

    public static String welcome() {
        return "Hello! I'm Duke!\n" + "What can I do for you?\n";
    }
}
