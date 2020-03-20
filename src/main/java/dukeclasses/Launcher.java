package dukeclasses;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Runs the main method.
     *
     * @param args runs method
     */
    public static void main(String[] args) {
        //It is possible to have 2 public static void main in a project. Just choose which psvm
        //to run from by selecting the top right.
        Application.launch(Main.class, args);
    }


}


