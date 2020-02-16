package duke;

import duke.commands.Parser;
import duke.commands.Storage;
import duke.commands.TaskList;
import duke.commands.Ui;
import duke.exceptions.DukeException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 * Contains the main function to run the programme.
 */
public class Duke extends Application {

    private static Parser parser;
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui = new Ui();
    private static Scanner scanner = new Scanner(System.in);

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass()
            .getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass()
            .getResourceAsStream("/images/DaDuke.png"));

    /**
     * Runs the programme.
     *
     * @param args arguments
     * @throws DukeException if the command cannot be parsed
     * @throws IOException if the file cannot be updated
     */
    public static void main(String[] args) throws DukeException, IOException {
        ui.dukePrint(ui.start());
        taskList = new TaskList();

        storage = new Storage("duke.txt", taskList);
        try {
            storage.retrieveInfo();
        } catch (FileNotFoundException e) {
            ui.dukePrint("Something went wrong: " + e.getMessage());
            return;
        }

        parser = new Parser(taskList);

        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            ui.dukePrint(parser.parse(command));
            command = scanner.nextLine();
        }

        ui.dukePrint("Bye. Hope to see you again soon!\n");
        storage.updateInfo();
    }

    @Override
    public void start(Stage stage) throws InterruptedException {
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        String dukeText = (ui.start()); //system.out
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, duke)
        );

        taskList = new TaskList();

        storage = new Storage("duke.txt", taskList);
        String output = "";
        try {
            storage.retrieveInfo();
        } catch (FileNotFoundException e) {
            output = ("Something went wrong: " + e.getMessage());
            dukeText = (output);
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(dukeText, duke)
            );
            return;
        }


        parser = new Parser(taskList);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput(stage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput(stage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane
                .setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog
     * container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    private void handleUserInput(Stage stage) throws InterruptedException {
        String userText = (userInput.getText());
        String dukeText = (getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, (user)),
                DialogBox.getDukeDialog(dukeText, (duke))
        );
        if (userInput.getText().equals("bye")) {
            TimeUnit.SECONDS.sleep(1);
            stage.close();
        }
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     *
     * @param command the user input
     * @return Duke's response
     */
    String getResponse(String command) {
        if (command.equals("bye")) {
            try {
                storage.updateInfo();
                return ("Bye. Hope to see you again soon!\n");
            } catch (IOException e) {
                return ("Something went wrong: " + e.getMessage());
            }
        } else {
            try {
                return parser.parse(command); //get strings from system.out
            } catch (DukeException e) {
                return ("OOPS!!! " + e.getMessage() + "\n");
            }
        }
    }
}
