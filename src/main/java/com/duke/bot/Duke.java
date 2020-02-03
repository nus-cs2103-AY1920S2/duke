package com.duke.bot;

import java.lang.String;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

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

    /*
    private Image user = new Image(this.getClass().getResourceAsStream("images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("images/DaDuke.png"));

     */

    /**
     * Creates a Duke Bot object.
     */
    public Duke() {
        this.tasks = TaskList.createTaskList();
        storage = Storage.createSrorageFile();
        this.ui = new DukeUi(System.in, System.out);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Step 1
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction( (event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

    }

    private Label getDialogLabel(String text) {
        Label label = new Label(text);
        label.setWrapText(true);

        return label;
    }

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

        try {
            duke.echo();
        } catch (DukeException e) {
            duke.ui.print(e.getMessage());
        }

        System.exit(0);
    }

}
