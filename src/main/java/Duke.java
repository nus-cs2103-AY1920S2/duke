import collection.TaskCollection;
import storage.PersistentStorageObserver;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import validator.CommandParser;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A chatbot interface.
 */
public class Duke extends Application {
    static String terminateCommand = "bye";
    static TaskCollection taskCollection = new TaskCollection();
    static PersistentStorageObserver persistentStorageObserver = new PersistentStorageObserver(taskCollection);
    // Use a hashmap to store command key to prevent developer error in typing command keys across different functions
    static HashMap<String, Integer> commandCodeMapping = new HashMap<String, Integer>() {
        {
            put("list", 0);
            put("mark done", 1);
            put("event", 2);
            put("todo", 3);
            put("deadline", 4);
            put("delete", 5);
            put("find", 6);
            put("invalid command", 7);
        }
    };
    static CommandParser commandParser = new CommandParser(commandCodeMapping);
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Switching logic for different commands.
     * @param command user command
     */
    private static String commandProcessor(String command) {
        try {
            Integer commandCode = commandParser.getCommandCode(command);
            String output = "";
            if (commandCode == commandCodeMapping.get("list")) {
                for (int i = 0; i < taskCollection.size(); i++) {
                    output = output + taskCollection.get(i).getFullDetail(i) + "\n";
                }
            } else if (commandCode == commandCodeMapping.get("mark done")) {
                Integer taskIndex = Integer.parseInt(command.split(" ")[1]);
                if (taskIndex < taskCollection.size()) {
                    taskCollection.get(taskIndex).markDone();
                }
            } else if (commandCode == commandCodeMapping.get("delete")) {
                Integer taskIndex = Integer.parseInt(command.split(" ")[1]);
                if (taskIndex < taskCollection.size()) {
                    taskCollection.remove(taskIndex.intValue());
                }
            } else if (commandCode == commandCodeMapping.get("find")) {
                String findKeyword = command.split(" ")[1];
                ArrayList<Task> matchTasks = taskCollection.find(findKeyword);
                for (int i = 0; i < matchTasks.size(); i++) {
                    output = output + matchTasks.get(i).getFullDetail(i) + "\n";
                }
            } else if (commandCode == commandCodeMapping.get("todo")) {
                String todoContent = command.split(" ", 2)[1];
                Task todo = new Todo(todoContent);
                taskCollection.add(todo);
                output = output + "ADD " + todo.getFullDetail(0) + "\n";
            } else if (commandCode == commandCodeMapping.get("deadline")) {
                String deadlineContent = command.split(" ", 2)[1];
                String deadlineDesc = deadlineContent.split("/by")[0];
                String deadlineTime = deadlineContent.split("/by")[1];
                Task deadline = new Deadline(deadlineDesc, deadlineTime);
                taskCollection.add(deadline);
                output = output + "ADD " + deadline.getFullDetail(0) + "\n";
            } else if (commandCode == commandCodeMapping.get("event")) {
                String eventContent = command.split(" ", 2)[1];
                String eventDesc = eventContent.split("/at")[0];
                String eventTime = eventContent.split("/at")[1];
                Task event = new Event(eventDesc, eventTime);
                taskCollection.add(event);
                output = output + "ADD " + event.getFullDetail(0) + "\n";
            } else if (commandCode == commandCodeMapping.get("invalid command")) {
                output = output + "OOPS Wrong command\n";
            }
            return output;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    /**
     * Long-polling for user commands.
     */
    private static void inputProcessor() {
        Scanner scanner = new Scanner(System.in);
        String userCommand = scanner.nextLine();

        while (!userCommand.equals(terminateCommand)) {
            String output = commandProcessor(userCommand);
            System.out.printf(output);
            userCommand = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Main entry to the bot program.
     * @param args external parameters.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        inputProcessor();
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

        // more code to be added here later

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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return commandProcessor(input);
    }

}
