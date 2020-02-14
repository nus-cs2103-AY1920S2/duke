//package java;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import parser.Command;
import parser.ExitCommand;
import parser.Parser;

import exceptions.IllegalDateTimeFormatException;
import exceptions.InvalidStorageFilePathException;
import exceptions.NoCommandException;
import exceptions.StorageOperationException;
import exceptions.NoDescriptionException;

import model.TaskList;

import storage.Storage;

import java.io.File;
import java.io.IOException;

/**
 * An application capable of recording the tasks and events to help the users
 * manage the schedule.
 */
public class Duke extends Application{

    protected String userName;
    protected Storage storage;
    protected TaskList taskList;
    protected Parser parser;
    protected Ui ui;

    public Duke() {

    }

    /**
     * Load the storage from file into internal task list.
     * Initiate other components.
     */
    public Duke(String userName) {
        this.userName = userName;
    }
    
    private void duke_start() {
        ui = new Ui();
        try {
            parser = new Parser();
            storage = new Storage();
            taskList = storage.load();
            ui.askForName();
            ui.greet();
        } catch (InvalidStorageFilePathException | IOException e) {
            ui.printErrorMessage(e.getMessage());
            throw new RuntimeException(e);
        } catch (StorageOperationException | NoDescriptionException | IllegalDateTimeFormatException err) {
            ui.printErrorMessage(err.getMessage());
        }
    }

    /**
     * Listen to the user input and take actions.
     */
    private void listen() {
        Command command = null;

        while (!ExitCommand.isExit(command)) {
            try {
                String input = ui.getUserInput();
                command = parser.parseCommand(input);
                command.setTaskList(taskList);
                ui.printCommandResult(command.execute());
                storage.save(taskList);
            } catch (NoDescriptionException | NoCommandException | IllegalDateTimeFormatException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (IOException e) {
                ui.printErrorMessage(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * exit with status 0.
     */
    private void exit() {
        System.exit(0);
    }

    @Override
    public void start(Stage stage) {

        Image image = new Image(".\\img\\Duke_waving.svg.png", 80, 100, false, false);
        Label duke_icon = new Label("Welcome to Duke!"); // Creating a new Label control

        duke_icon.setGraphic(new ImageView(image));
        duke_icon.setFont(new Font("Arial", 50));

        Scene scene = new Scene(duke_icon); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    private void run() {
        duke_start();
        listen();
        exit();
    }

    public static void main(String[] args) {
        Duke myDuke = new Duke();
        myDuke.run();
    }
}
