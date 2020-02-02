import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

/**
 * The Duke class is the main class,
 * where the command processing happens.
 */

public class Duke extends Application {

    private static Ui uI;
    private static Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /* */

    /**
     * Construct a new Duke instance.
     * First greets the user and then loads the task data from file.
     *//*
    public Duke() {
        Storage storage = new Storage();
        uI = new Ui();
        parser = new Parser();
        uI.greet();
        while (true) {
            try {
                tasks = storage.loadFile();
                run();
                storage.saveFile(tasks);
                System.exit(0);
            } catch (IOException | DukeException e) {
                uI.printError(e);
            } catch (DateTimeParseException d) {
                uI.printInvalidDateFormatError();
            }
        }
    }*/
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

        // more code to be added here later
    }

    private static List<Task> tasks = new ArrayList<>();

    /**
     * Parses the input entered by the client.
     * The following are valid commands that Duke can process:
     * list - lists all the tasks that are stored.
     * done [index] - marks the task of a particular index as done.
     * delete [index] - deletes the task of a particular index.
     * todo [description] - adds the Todo task to the list.
     * deadline [description] /by [date] [time] - adds the Deadline task to the list.
     * event [description] /at [date] [time] - adds the Event task to the list.
     * If an exit command is entered, it is processed,
     * then the goodbye message is printed and the program exits from the loop.
     *
     * @throws DukeException    If the command is invalid or the task enquired doesn't exists.
     * @throws DateTimeParseException If the date of the deadline or event is not formatted properly.
     */
    private static void run() throws DukeException, DateTimeParseException {
        TaskList taskList = new TaskList(tasks);
        String command = parser.getCommand();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                uI.displayTasks(tasks);
            } else if (command.contains("find")) {
                uI.displayFoundTasks(taskList.findTask(parser.trimCommand("find", command)));
            } else {
                switch (parser.checkCommand(command)) {
                case "done":
                    tasks = taskList.markAsDone(command);
                    break;
                case "delete":
                    tasks = taskList.deleteTask(command);
                    break;
                default:
                    tasks = taskList.addTask(command);
                    break;
                }
            }
            command = parser.getCommand();
        }
        uI.printBye();
    }

    public static void main(String[] args) {
        new Duke();
    }

}

