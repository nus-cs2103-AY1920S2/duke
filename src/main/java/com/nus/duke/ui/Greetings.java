package com.nus.duke.ui;

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

    public static void init() {
        System.out.println(WELCOME_LOGO);
        System.out.println(WELCOME_TEXT);
    }

    public static String prettyPrint(String s) {
        String formattedString = String.format(   "-----------------------------"
                                                + "%s"
                                                + "-----------------------------", s);
        return formattedString;
    }
}
