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
    private TaskList taskList;
    private Boolean isClosed = false;

    // storage path is set to root_folder/storage/file.txt
    private static Storage storage = new Storage(Paths.get("storage", "file.txt"));

    public Duke() {
        this.taskList = new TaskList(Duke.storage.getTasksFromStorage());
    }

    public static void main(String[] args) {}

    /** duke's start function for JavaFX */
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param input user input
     * @return String duke's response to user input
     */
    public String getResponse(String input) {
        try {
            String output = dispatch(input.trim());
            Duke.storage.update(this.taskList.getAllTask());
            return output;
        } catch (DukeException err) {
            return err.getMessage();
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
            if (this.taskList.isEmpty()) {
                return "Hi user! You currently have no tasks!";
            }
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
                }

                int taskIndex = Parser.getTaskIndex(input) - 1;
                if (taskIndex >= this.taskList.size()) {
                    throw new TaskListException(this.taskList.size());
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
                        "Got it. I've added this task:%n%s%nNow you have %d %s in your list.",
                        newTask.toString(),
                        this.taskList.size(),
                        this.taskList.size() > 1 ? "tasks" : "task");
            }
        }
    }

    /** @return Boolean whether the user has closed the GUI */
    public Boolean getIsClosed() {
        return this.isClosed;
    }
}
