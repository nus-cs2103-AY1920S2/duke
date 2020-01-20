//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;

import java.util.*;

public class Duke { //extends Application {
    public static void greetings() {
                String intro = "Hello! I'm Grapie\n" +
                "     What can I do for you?\n" ;

                formattingDivider(intro);

    }

    public static void formattingDivider(String contentStr) {
        System.out.println("    #__________________________________________________________#");
        System.out.println("    " + contentStr);
        System.out.println("    #__________________________________________________________# \n");

    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String nextStr = sc.nextLine();

        List<String> storingList = new ArrayList<>();

        if (nextStr.equals("list")) {
            //formattingDivider("added: " + nextStr);
            
        } else {
            while (!nextStr.equals("bye")) {
                formattingDivider("added: " + nextStr);

                storingList.add(nextStr);
                nextStr = sc.nextLine();
            }
        }

        formattingDivider("Okie!! Goodbye!");
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
