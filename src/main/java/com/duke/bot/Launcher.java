package com.duke.bot;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    public static void main(String[] args) {
        //Application.launch(Duke.class, args);
        Application.launch(Main.class, args);
    }

}