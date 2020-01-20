//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;

import java.util.*;

public class Duke { //extends Application {
    public static void greetings() {
        System.out.println("    #__________________________________________________________#\n" +
                "     Hello! I'm Grapie\n" +
                "     What can I do for you?\n" +
                "    #__________________________________________________________#");
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);

        String nextStr = sc.next();

        while (!nextStr.equals("bye")) {
            System.out.println("    #__________________________________________________________#");
            System.out.println("    " + nextStr);
            System.out.println("    #__________________________________________________________#");

            nextStr = sc.next();
        }

        System.out.println("    #__________________________________________________________#");
        System.out.println("    Goodbye!! Hope to see you again soon!");
        System.out.println("    #__________________________________________________________#");
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        greetings();
        echo();
    }

//    @Override
//    public void start(Stage stage) {
//        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//    }
}
