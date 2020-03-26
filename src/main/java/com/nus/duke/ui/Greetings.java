package com.nus.duke.ui;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Greetings {
    private static final String WELCOME_LOGO    = " ____         _        \n"
                                                + "|  _ \\ _  __| | _____ \n"
                                                + "| | | | | |  | |/ / _ \\\n"
                                                + "| |_| | |_|  |   < ___/\n"
                                                + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_TEXT    = "-----------------------------\n"
                                                + "Hello, I am Duke\n"
                                                + "What can I do for you?\n"
                                                + "-----------------------------\n";

    private static final String EXIT_TEXT       = "-----------------------------\n"
                                                + "Goodbye\n"
                                                + "-----------------------------\n";

    private static final String UNDEFINED       = "-----------------------------\n"
                                                + "Input is undefined. Try again\n"
                                                + "-----------------------------\n";
    /**
     * <p> Welcome text logo and starting screen </p>
     */
    public static void init() {
        System.out.println(WELCOME_LOGO);
        System.out.println(WELCOME_TEXT);
    }

    /**
     * <p> Give the user a nice exit </p>
     */
    public static void tearDown() {
        System.out.println(EXIT_TEXT);
    }

    /**
     * <p> If the user gives an unknown input </p>
     */
    public static void handleUndefined() {
        System.out.println(UNDEFINED);
    }

    /**
     * <p> Prettily formats a string with separators </p>
     *
     * @param s String argument
     */
    public static void prettyPrint(String s) {
        String formattedString = String.format(   "-----------------------------\n"
                                                + "%s\n"
                                                + "-----------------------------\n", s);
        System.out.println(formattedString);
    }

    /**
     * <p> Prettily prints a list of strings with separators </p>
     *
     * @param strs  List of strings to be displayed
     * @see com.nus.duke.ui.Greetings#prettyPrint(String)
     */
    public static void prettyPrint(List<String> strs) {
        final AtomicInteger ctr = new AtomicInteger();
        StringBuilder strBldr = new StringBuilder();
        strBldr.append("-----------------------------\n");

        strs.forEach(str -> strBldr.append(String.format("%d. ", ctr.getAndIncrement()))
                                    .append(str)
                                    .append("\n"));

        strBldr.append("-----------------------------\n");
        System.out.println(strBldr.toString());
    }
}
