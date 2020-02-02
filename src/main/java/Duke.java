import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class is the main point of entry for this project.
 */
public class Duke extends Application {
    private final Scanner sc = new Scanner(System.in);
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();
    private final Ui ui = new Ui();

    private void greet() {
        String greeting = "Hello, I am Duke " + new String(Character.toChars(0x1F481)) + ", your personal assistant.";
        ui.output(greeting);
    }

    private void initialise() {
        ArrayList<String> lines = this.storage.readFromDisk();
        for (String line : lines) {
            Parser parser = new Parser();
            parser.parseDiskData(line);
            Command command = parser.getCommand();

            if (command == Command.ADD_TODO) {
                Todo todo = new Todo(parser.getDescription(), parser.getIsDone());
                this.taskList.addTask(todo);
            } else if (command == Command.ADD_DEADLINE) {
                Deadline deadline = new Deadline(parser.getDescription(), parser.getIsDone(), parser.getDate());
                this.taskList.addTask(deadline);
            } else if (command == Command.ADD_EVENT) {
                Event event = new Event(parser.getDescription(), parser.getIsDone(), parser.getDate());
                this.taskList.addTask(event);
            }
        }
    }

    private void getCommands() {
        while (true) {
            String userInput = this.sc.nextLine().trim();
            try {
                Parser parser = new Parser();
                parser.parseUserInput(userInput);
                Command command = parser.getCommand();

                if (command == Command.EXIT_DUKE) {
                    break;
                } else if (command == Command.LIST_TASKS) {
                    this.ui.output(this.taskList.listTasks());
                } else if (command == Command.ADD_TODO) {
                    Todo todo = new Todo(parser.getDescription(), false);
                    this.taskList.addTask(todo);
                    this.ui.output("Added: " + todo.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.writeToDisk("T|0|" + parser.getDescription());
                } else if (command == Command.ADD_DEADLINE) {
                    Deadline deadline = new Deadline(parser.getDescription(), parser.getIsDone(), parser.getDate());
                    this.taskList.addTask(deadline);
                    this.ui.output(
                            "Added: " + deadline.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.writeToDisk("D|0|" + parser.getDescription() + "|" + parser.getDate().toString());
                } else if (command == Command.ADD_EVENT) {
                    Event event = new Event(parser.getDescription(), parser.getIsDone(), parser.getDate());
                    this.taskList.addTask(event);
                    this.ui.output("Added: " + event.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.writeToDisk("E|0|" + parser.getDescription() + "|" + parser.getDate().toString());
                } else if (command == Command.MARK_TASK_AS_DONE) {
                    Task task = this.taskList.markAsDone(parser.getTaskIndex());
                    this.ui.output(
                            "Marked as done: " + task.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.markAsDone(parser.getTaskIndex());
                } else if (command == Command.DELETE_TASK) {
                    Task task = this.taskList.removeTask(parser.getTaskIndex());
                    this.ui.output("Deleted: " + task.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.removeTask(parser.getTaskIndex());
                } else if (command == Command.FIND_TASKS) {
                    this.ui.output(this.taskList.findTasks(parser.getDescription()));
                }
            } catch (DukeException e) {
                this.ui.output(e.getMessage());
                continue;
            }
        }
        sc.close();
    }

    private void exit() {
        this.ui.output("Bye! " + new String(Character.toChars(0x1F44B)));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.initialise();

        duke.greet();

        duke.getCommands();

        duke.exit();
    }

    /**
     * Iteration 2: Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to the dialog container. Clears
     * the user input after processing.
     */
    private void handleUserInput(TextField userInput, VBox dialogContainer, Image duke, Image user) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(this.getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    @Override
    public void start(Stage stage) {
        // step 1
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        // step 2
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

        // step 3
        Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

        sendButton.setOnMouseClicked((event) -> {
            this.handleUserInput(userInput, dialogContainer, duke, user);
        });

        userInput.setOnAction((event) -> {
            this.handleUserInput(userInput, dialogContainer, duke, user);
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
}
