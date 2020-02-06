package duke;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import duke.parser.*;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.*;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

/**
 * Main UI method.
 */
public class Duke extends Application {
    public Button sendButton;
    public ScrollPane scrollPane;
    public AnchorPane mainLayout;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Gui gui;

    /**
     * If no file path specified, default path is assumed
     */
    public Duke() {
        this(true);
    }

    private Duke(boolean gui) {
        this(Storage.DEFAULT_PATH, gui);
    }

    /**
     * constructor to specify file path of the last saved data
     *
     * @param filePath = path of last saved data file
     * @param gui      = using gui?
     */
    public Duke(String filePath, boolean gui) {
        this.ui = new Ui(new Scanner(System.in));
        this.storage = new Storage(filePath);
        if (this.storage.fileExist()) {
            try {
                this.tasks = TaskList.fromCSVList(storage.loadCSVList());
            } catch (Exception e) {
                this.ui.respond(Ui.loadingErrorMsg);
                tasks = new TaskList();
            }
        } else {
            tasks = new TaskList();
        }
        if (gui) {
            this.gui = new Gui(this.dialogContainer, this.userInput);
        }
    }

    /**
     * Main method.
     */
    public void run() {
        ui.respond(Ui.greetings);
        Command cmd;
        while (ui.hasNextLine()) {
            cmd = Parser.parse(ui.nextLine());
            cmd.execute(this.tasks, this.ui, this.storage);
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            new Duke(args[0], false).run();
        } else {
            new Duke(false).run();
        }
    }

    @FXML
    protected void handleSendButtonAction(MouseEvent event) {
        Command cmd = Parser.parse(gui.getUserInputText());
        cmd.execute(this.tasks, this.gui, this.storage);
        this.gui.clearUserInput();
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src/main/java/duke/fxml/main.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("src/main/java/duke/fxml/main.fxml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
/*
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */

}
