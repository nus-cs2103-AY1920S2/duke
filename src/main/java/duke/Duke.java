package duke;

import task.Task;
import storage.Storage;
import exception.DukeException;
import parser.Parser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Duke extends Application {
    private UserInterface UI;
    private TaskList taskList;

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

    public String getResponse(String input) {
        dispatch(input);
    }

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

    private void dispatch(String input) throws DukeException {
        switch (input) {
            case "list":
                UI.showList(this.taskList.getAllTaskString());
                return;
            case "bye":
                UI.showBye();
                return;
            default:
                // as long as done/delete inside
                if (Parser.isDoneDelete(input)) {
                    if (this.taskList.isEmpty()) {
                        throw new DukeException("Task list is empty!");
                    }
                    // Shift to parser to check for length
                    String[] splitInput = input.split(" ");
                    int taskIndex = Integer.parseInt(splitInput[splitInput.length - 1]) - 1;
                    if (taskIndex >= this.taskList.size()) {
                        throw new DukeException(String.format(
                                "Please choose an index that is between 1 and %d (inclusive)",
                                this.taskList.size()));
                    }
                    if (input.contains("done")) {
                        this.taskList.markDone(taskIndex);
                        UI.out2("Nice! I've marked this task as done:");
                        UI.out3(this.taskList.getTask(taskIndex));
                    } else {
                        Task removedTask = this.taskList.popTask(taskIndex);
                        UI.out2("Noted. I've removed this task:");
                        UI.out3(removedTask.toString());
                        UI.out2(String.format("Now you have %d tasks in the list.",
                                this.taskList.size()));
                    }
                } else if (Parser.isFind(input)) {
                    if (this.taskList.isEmpty()) {
                        throw new DukeException("Task list is empty!");
                    }
                    String searchTerm = input.substring(5).trim();
                    UI.showSearch(taskList.search(searchTerm));
                } else {
                    Task newTask = this.taskList.addTask(input);
                    UI.out2("Got it. I've added this task: ");
                    UI.out3(newTask.toString());
                    UI.out2(String.format("Now you have %d %s in the list.", this.taskList.size(),
                            this.taskList.size() > 1 ? "tasks" : "task"));
                }
                Duke.storage.update(this.taskList.getAllTask());
        }
    }
}
