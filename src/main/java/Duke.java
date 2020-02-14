//package java;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


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

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
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
