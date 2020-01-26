//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Duke { //extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException, GrapieExceptions {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(), storage);
        ui = new Ui();
    }

    public void runDuke() throws IOException {
        ui.greetings();

        //Scanner class for input
        Scanner sc = new Scanner(System.in);
        String nextStr = sc.nextLine();

        //loop
        while (!nextStr.equals("bye")) { //check for ending input
            ui.readCommand(nextStr, tasks);
            nextStr = sc.nextLine();
        }

        ui.sayonara();

    }

    public static void main(String[] args) throws IOException {
        Duke duke = null;
        try {
            duke = new Duke("data/dukeStorage.txt");
        } catch (GrapieExceptions grapieExceptions) {
            System.out.println("    #__________________________________________________________# \n");
            System.out.println("      " + grapieExceptions);
            System.out.println("    #__________________________________________________________#");
        }
        duke.runDuke();
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
