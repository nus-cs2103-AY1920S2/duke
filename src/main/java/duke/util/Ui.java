package duke.util;

import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);
    String entry = "";
    public static final String LINE_DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    public static final String welcomeText = setBorder("Hello! I'm Johnny\nWhat can I do for you?");

    public String input() {
        return sc.nextLine();
    }

    public static String setBorder(String s) {
        return LINE_DIVIDER + s + "\n" + LINE_DIVIDER;
    }

    /**
     * prints welcome message when application is launched.
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(setBorder("Hello! I'm Duke\nWhat can I do for you?"));
    }

    public static void bye() {
        System.out.println(setBorder("Bye! Hope to see you again soon."));
    }
}
