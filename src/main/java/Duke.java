//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;

import java.util.*;

public class Duke { //extends Application {
    public static void greetings() {
        String intro = "    Hello! I'm Grapie\n" +
                "    What can I do for you?\n";

        formattingDivider(intro);

    }

    public static void formattingDivider(String contentStr) {
        System.out.println("    #__________________________________________________________#");
        System.out.println(contentStr);
        System.out.println("    #__________________________________________________________# \n");

    }

    /**
     * Function to change ✗ to ✓
     * @param uncompletedTask
     * @return
     */
    public static String completetTask(String uncompletedTask) {
        return "ha"; //dummy
    }

    public static void echo() {
        //scanner system to take in input
        Scanner sc = new Scanner(System.in);
        String nextStr = sc.nextLine();

        //arraylist to store list
        List<String> storingList = new ArrayList<>();

        while (!nextStr.equals("bye")) { //check for ending input
            if (nextStr.length() >= 6 && nextStr.substring(0, 4).equals("done")) {
                //can we assume its always a number after done?
//                int numberDone =

                //System.out.println("ha ahhahaha");
            } else if (nextStr.equals("list")) { //check for listing input

                int sizeOfList = storingList.size();
                String stringList = "     Here are the tasks in your list: \n";

                for (int i = 1; i <= sizeOfList; i++) {
                    stringList = stringList + "    " + i + ". [X] " + storingList.get(i - 1) + "\n"; //add tasks
                }

                formattingDivider(stringList);
            } else { //echo
                formattingDivider("    added: " + nextStr);

                storingList.add(nextStr);
            }

            nextStr = sc.nextLine(); //next input
        }


        formattingDivider("    Okie!! Goodbye!");
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
