package duke.ui;

import java.util.Scanner;

public class Ui {

    private static final String INDENT = "    ";
    private static final String HOR_LINE = "___________________________" +
            "_________________________________";

    public static void showLoadingError() {
        System.out.println(INDENT + "Loading error");
    }

    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(INDENT + HOR_LINE);
        System.out.println(INDENT + "Hello! I'm Duke!");
        System.out.println(INDENT + "What can I do for you?");
    }

    public static void showError(String message) {
        System.out.println(INDENT + message);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    public static void print(String s) {
        System.out.println(INDENT + s);
    }
}
