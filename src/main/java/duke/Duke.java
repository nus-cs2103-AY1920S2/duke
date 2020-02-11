package duke;

import collection.TaskCollection;
import storage.PersistentStorageObserver;
import service.ReminderObserver;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import validator.CommandParser;
import view.MainWindow;
import view.DialogBox;

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
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A chatbot interface.
 */
public class Duke {
    static String terminateCommand = "bye";
    static TaskCollection taskCollection = new TaskCollection();
    static PersistentStorageObserver persistentStorageObserver = new PersistentStorageObserver(taskCollection);
//    static ReminderObserver reminderObserver = new ReminderObserver(taskCollection);
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
                ArrayList<String> parsedParams = commandParser.parseTaskDescTime(command, "/by");
                Task deadline = new Deadline(parsedParams.get(0), parsedParams.get(1), parsedParams.get(2));
                taskCollection.add(deadline);
                output = output + "ADD " + deadline.getFullDetail(0) + "\n";
            } else if (commandCode == commandCodeMapping.get("event")) {
                ArrayList<String> parsedParams = commandParser.parseTaskDescTime(command, "/by");
                Task event = new Event(parsedParams.get(0), parsedParams.get(1), parsedParams.get(2));
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return commandProcessor(input);
    }

}
