import dukecommand.DukeCommand;
import dukeexceptions.DukeException;
import dukelist.DukeList;
import dukeparser.DukeParser;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * Represents a Duke object that runs the main function of the program
 * Will run until user inputs 'bye' command or force quits the program.
 */
public class Duke extends Application {
    private DukeList dl;
    private DukeStorage ds;
    private DukeUI ui;
    private DukeParser parser;


    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Constructor for Duke. Each instantiation will create a new
     * DukeStorage, DukeList, DukeUI, DukeParser for the object.
     */
    public Duke() {
        try {
            ds = new DukeStorage();
            dl = ds.load();
            ui = new DukeUI();
            parser = new DukeParser();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * Drives the body of the code.
     */
    public void run() {
        ui.showWelcomeMessage();
        String command;
        boolean isMainExit = false;

        while (!isMainExit) {
            try {
                command = ui.readCommandString();
                ui.printLine();
                DukeCommand currCommand = parser.handleCommand(command);
                currCommand.execute(dl, ds, ui);
                isMainExit = currCommand.getIsExit();
                ui.printLine();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

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
}
