package duke;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import duke.javafx.DialogBox;
import duke.task.TaskList;
import duke.exception.InvalidCommandException;

/**
 * Main class for the Duke chatbot application.
 */
public class Duke extends Application {
    private TaskList tasks;
    private Storage storage;
    private String fileName;
    private Parser parser;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {}

    /**
     * Constructs a fresh Duke instance with a supplied file storing the task list.
     * @param fileName Path to the file storing the task list.
     */
    public Duke(String fileName) {
        this.fileName = fileName;
        storage = new Storage(fileName);
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser();
        ui = new Ui();
        File file = new File(this.fileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            Ui.printLines("File creation failed.");
        }
    }
    
    public static void main(String[] args) {
        new Duke("src/main/data/tasks.txt").run();
    }

    private void run() {
        ui.printIntro();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                String[] split = input.split(" ");
                String command = split[0];

                if (parser.parseCommand(command, "list")) {
                    System.err.println(tasks);
                } else if (parser.parseCommand(command, "bye")) {
                    ui.printGoodbye();
                    break;
                } else if (parser.parseCommand(command, "delete")) {
                    int idx = Integer.parseInt(split[1]);
                    tasks.deleteTask(idx - 1);
                    storage.deleteTask(idx);
                } else if (parser.parseCommand(command, "done")) {
                    int idx = Integer.parseInt(split[1]);
                    tasks.doTask(idx - 1);
                    storage.doTask(idx);
                } else if (parser.parseCommand(command, "find")) {
                    String results = tasks.findKeyword(input);
                    Ui.printLines(results);
                } else if (parser.parseCommand(command, "todo")) {
                    tasks.manageTodo(storage, input, fileName);
                } else if (parser.parseCommand(command, "event")) {
                    tasks.manageEvent(storage, input, fileName);
                } else if (parser.parseCommand(command, "deadline")) {
                    tasks.manageDeadline(storage, input, fileName);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                Ui.printLines("Sorry, invalid command. Try again with the following:\n     todo, deadline, event");
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printLines("Sorry, invalid syntax or command. Please try again!");
            }
        }

        sc.close();
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
    
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        styleControls(stage, mainLayout);
        scene = new Scene(mainLayout);

        setupHandlers();

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    
        stage.setScene(scene);
        stage.show();
    }

    private void styleControls(Stage stage, AnchorPane mainLayout) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);
        
        scrollPane.setPrefSize(400, 535);
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
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void setupHandlers() {
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        
        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}