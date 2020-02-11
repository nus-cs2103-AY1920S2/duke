package duke;

import exception.DukeException;
import exception.TaskListException;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;
import task.Task;

public class Duke extends Application {
    private UserInterface UI;
    private TaskList taskList;
    private Boolean isClosed = false;

    // storage path is set to root_folder/storage/file.txt
    private static Storage storage = new Storage(Paths.get("storage", "file.txt"));

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public Duke() {
        this.UI = new UserInterface();
        this.taskList = new TaskList(Duke.storage.getTasksFromStorage());
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.start();
    }

    /**
     * Keeps scanning for user input until bye command is input Also handles all DukeExceptions and
     * outputs it
     */
    public void start() {
        while (!UI.isExit()) {
            String input = UI.getInput();
            UI.showSep();
            try {
                dispatch(input);
            } catch (DukeException err) {
                UI.showErr(err.getMessage());
            } finally {
                UI.showSep();
            }
        }
    }

    /**
     * This is the main logic for handling user input , it dispatches actions based on the input and
     * uses the Parser to handle more complex commands
     *
     * @param input trimmed user input
     * @throws DukeException Exceptions arising from incorrect user input
     */
    private String dispatch(String input) throws DukeException {
        switch (input) {
            case "list":
                return this.taskList
                        .getAllTasksAsString()
                        .stream()
                        .collect(Collectors.joining(String.format("%n")));
            case "bye":
                isClosed = true;
                return "Bye see you again soon!";
            default:
                // as long as done/delete inside
                if (Parser.isDoneOrDelete(input)) {
                    if (this.taskList.isEmpty()) {
                        throw new TaskListException();
                        // throw new DukeException("Task list is empty!");
                    }
                    int taskIndex = Parser.getTaskIndex(input) - 1;
                    if (taskIndex >= this.taskList.size()) {
                        throw new TaskListException(this.taskList.size());
                        // throw new DukeException(
                        //         String.format(
                        //                 "Please choose an index that is between 1 and %d
                        // (inclusive)",
                        //                 this.taskList.size()));
                    }
                    if (input.contains("done")) {
                        this.taskList.markDone(taskIndex);
                        return String.format(
                                "Nice! I've marked this task as done:%n%s",
                                this.taskList.getTask(taskIndex));
                    } else {
                        Task removedTask = this.taskList.popTask(taskIndex);
                        return String.format(
                                "Noted. I've removed this task:%n%s%nNow you have %d tasks in the list.",
                                removedTask.toString(), this.taskList.size());
                    }
                } else if (Parser.isFind(input)) {
                    if (this.taskList.isEmpty()) {
                        throw new TaskListException();
                        // throw new DukeException("Task list is empty!");
                    }
                    String searchTerm = Parser.getSearchTerm(input);
                    String searchResults =
                            taskList.search(searchTerm)
                                    .stream()
                                    .collect(Collectors.joining(String.format("%n")));
                    if (searchResults.isEmpty()) {
                        return "No matching tasks!";
                    }
                    return searchResults;
                } else {
                    Task newTask = Task.newTask(input);
                    this.taskList.addTask(newTask);
                    return String.format(
                            "Got it. I've added this task:%n%s%nNow you have %d %s in the list,",
                            newTask.toString(),
                            this.taskList.size(),
                            this.taskList.size() > 1 ? "tasks" : "task");
                }
        }
    }

    /**
     * @param input user input
     * @return String duke's response to user input
     */
    public String getResponse(String input) {
        try {
            String output = dispatch(input);
            Duke.storage.update(this.taskList.getAllTask());
            return output;
        } catch (DukeException err) {
            return err.getMessage();
        }
    }

    /** @return Boolean whether the user has closed the GUI */
    public Boolean getIsClosed() {
        return this.isClosed;
    }
}
