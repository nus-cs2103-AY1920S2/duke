package com.duke.bot;

import java.lang.String;
import java.time.LocalDate;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the Duke bot that manages the tasks of the users.
 */
public class Duke extends Application {
    private TaskList tasks;
    private DukeUi ui;
    private Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a Duke Bot object.
     */
    public Duke() {
        this.tasks = TaskList.createTaskList();
        storage = Storage.createSrorageFile();
        this.ui = new DukeUi(System.in, System.out);
    }


    /**
     * Greet the user with a welcome message.
     */

    @Override
    public void start(Stage stage) throws Exception {

        System.out.println("flag 1");
        // Step 1
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        //Label dukeText = new Label(ui.greet());
        String dukeText = ui.greet();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeText, dukeImage));
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2
        stage.setTitle("duke.Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        //Label userText = new Label(userInput.getText());
        //Label dukeText = new Label(getResponse(userInput.getText()));
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, (userImage)),
                DialogBox.getDukeDialog(dukeText, (dukeImage))
        );
        userInput.clear();
    }


    private Label getDialogLabel(String text) {
        Label label = new Label(text);
        label.setWrapText(true);

        return label;
    }

    public String getResponse(String input) {
        String output = "";
        assert output.isEmpty() : "output should be empty initially";
        Scanner sc = new Scanner(input);
        String commandWord = sc.next();
        String restOfStr = sc.hasNext() ? sc.nextLine() : "";
        try {
            output = Parser.parse(commandWord, new DukeUi(restOfStr), storage, tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }

        return output;
    }

    /**
     * Creates a Duke object.
     *
     * @param ui the UI that duke will be running on
     */
    public Duke(DukeUi ui) {
        this.tasks = TaskList.createTaskList();
        storage = Storage.createSrorageFile();
        this.ui = ui;
    }

    /**
     * Takes the user's input and decides the action to be taken by Duke bot.
     */
    public void echo() throws DukeException {
        String userCommand = ui.getNext();
        assert !userCommand.equals("") : "user command cannot be empty";
        String output = Parser.parse(userCommand, ui, storage, tasks);
        ui.print(output);
        if (!userCommand.equals("bye")) {
            echo();
        }
    }

    /**
     * The main method to simulate and run Duke Bot.
     *
     * @param args The arguments written by the user in the command line.
     */
    public static void main(String[] args) {
        Duke duke  = new Duke(new DukeUi());

        duke.ui.greet();
        //System.out.println(System.getProperty("user.dir"));

        try {
            duke.echo();
        } catch (DukeException e) {
            duke.ui.print(e.getMessage());
        }

        System.exit(0);
    }

}
