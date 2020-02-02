package dukeclasses;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */


public class Launcher {
    public static void main(String[] args) {
        //Can have 2 public static void main in a project. Just choose which psvm
        //to run from by selecting the top right.
        Application.launch(Duke.class, args);
    }


}


