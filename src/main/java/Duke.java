//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;

import java.util.*;

public class Duke { //extends Application {

    public static void main(String[] args) { //throws DukeExceptions {
        //Methods implemented in diff levels
        LevelMethods levelMethods = new LevelMethods();
        levelMethods.greetings(); //greet

        //Scanner class for input
        Scanner sc = new Scanner(System.in);
        String nextStr = sc.nextLine();

        //loop

        while (!nextStr.equals("bye")) { //check for ending input

            if (nextStr.length() >= 4 && nextStr.substring(0, 4).equals("done")) {
                //can we assume its always a number after done?

//                    String strNumberDone = nextStr.substring(5, nextStr.length());
//                    int numDone = Integer.parseInt(strNumberDone); //convert to number

                    try {
                        //levelMethods.completeTask(numDone);
                        levelMethods.completeTask(nextStr);
                    } catch (GrapieExceptions ex) {
                        System.out.println("    #__________________________________________________________# \n");
                        System.out.println("      " + ex);
                        System.out.println("    #__________________________________________________________#");
                    }
            } else if (nextStr.equals("list")) {
                //check for listing input
                levelMethods.listTheList();

            } else if (nextStr.length() >= 6 && nextStr.substring(0, 6).equals("delete")) {
                //delete
                try {
                    levelMethods.deleteTask(nextStr);
                } catch (GrapieExceptions ex) {
                    System.out.println("    #__________________________________________________________# \n");
                    System.out.println("      " + ex);
                    System.out.println("    #__________________________________________________________#");
                }
            } else {
                //echo
                try {
                    levelMethods.echo(nextStr);
                } catch (GrapieExceptions ex) {
                    //System.out.println("yes");
                    //System.out.println(ex);
                    System.out.println("    #__________________________________________________________# \n");
                    System.out.println("      " + ex);
                    System.out.println("    #__________________________________________________________#");
                }
            }


            nextStr = sc.nextLine();
        }


        levelMethods.sayonara(); //goodbye

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
