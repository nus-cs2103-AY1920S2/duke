import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

/**
 * Duke is a command line application that lets users store tasks they wish to track.
 * We have here our driver class which initialize other main components of the app.
 */
public class Duke  {
// extends Application
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * A constructor which takes instantiates other components.
     * @param filePath Path name of the file we want to load.
     */
    public Duke(String filePath) {
        this.ui = new Ui(new Scanner(System.in));
        this.storage = new Storage(filePath);
        this.tasks = storage.load();
        this.parser = new Parser();
    }

    /**
     * Handles the printing of welcome and exit messages.
     * Running of app is abstracted by a method to keep things simple here.
     */
    public void run() {
        ui.showWelcome();
        runUntilExit();
        ui.showExit();
    }

    private void runUntilExit() {
        while (true) {
            String commandText = ui.getUserCommand();
            if (commandText.equals("bye")) {
                break;
            }
            parser.handleTaskCommand(commandText, tasks);
            ui.showLine();
        }
        this.storage.save(tasks);
    }

    /**
     * Main is the driver function that creates an instance of Duke for running.
     * @param args Command line arguments, simply here by convention.
     */
    public static void main(String[] args) {
        new Duke("data\\duke.txt").run();
        // launch();
    }

//    /**
//     * The main entry point for all JavaFX applications.
//     * The start method is called after the init method has returned,
//     * and after the system is ready for the application to begin running.
//     *
//     * <p>
//     * NOTE: This method is called on the JavaFX Application Thread.
//     * </p>
//     *
//     * @param primaryStage the primary stage for this application, onto which
//     *                     the application scene can be set.
//     *                     Applications may create other stages, if needed, but they will not be
//     *                     primary stages.
//     * @throws Exception if something goes wrong
//     */
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        primaryStage.setScene(scene); // Setting the stage to show our screen
//        primaryStage.show(); // Render the stage.
//    }
}
