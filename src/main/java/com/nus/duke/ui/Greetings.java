package com.nus.duke.ui;

import java.util.List;

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

    public static void init() {
        System.out.println(WELCOME_LOGO);
        System.out.println(WELCOME_TEXT);
    }

    public static void tearDown() {
        System.out.println(EXIT_TEXT);
    }

    public static void handleUndefined() {
        System.out.println(UNDEFINED);
    }

    public static void prettyPrint(String s) {
        String formattedString = String.format(   "-----------------------------\n"
                                                + "%s\n"
                                                + "-----------------------------\n", s);
        System.out.println(formattedString);
    }

    public static void prettyPrint(List<String> strs) {
        StringBuilder strBldr = new StringBuilder();
        strBldr.append("-----------------------------");
        strs.forEach(str -> strBldr.append(str));
        strBldr.append("-----------------------------");
        System.out.println(strBldr.toString());
    }
}
