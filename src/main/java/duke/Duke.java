package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke is a task-management system. It accept user commands to add/delete tasks, mark tasks as done and list all tasks.
 * Upon exit, tasks will be saved and kept in a file for the next run.
 */
public class Duke extends Application {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructs a Duke object. An attempt will be made to load in tasks from the file specified. If the file
     * does not exist or cannot be accessed, an empty task list will be loaded.
     */
    public Duke() {
        Path filePath = Paths.get("data", "duke.txt");
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }

    /**
     * Starts Duke. User will be able to input commands to perform actions.
     */
    public void run() {
        ui.showGreeting();

        loop:
        while (true) {
            try {
                String input = ui.getInput();
                Parser instruction = new Parser(input);
                Command command = instruction.getCommand();

                switch (command) {
                case BYE:
                    break loop;
                case DONE:
                    int doneTaskNum = Integer.parseInt(instruction.getParameter());
                    tasks.setAsDone(doneTaskNum);
                    ui.showDoneTask(tasks.getTask(doneTaskNum));
                    break;
                case TODO:
                    Task newToDo = new ToDo(instruction.getDescription());
                    tasks.addToTasks(newToDo);
                    ui.showAddedTask(tasks, newToDo);
                    break;
                case DEADLINE:
                    Task newDeadline = new Deadline(instruction.getDescription(), instruction.getDate());
                    tasks.addToTasks(newDeadline);
                    ui.showAddedTask(tasks, newDeadline);
                    break;
                case EVENT:
                    Task newEvent = new Event(instruction.getDescription(), instruction.getDate());
                    tasks.addToTasks(newEvent);
                    ui.showAddedTask(tasks, newEvent);
                    break;
                case LIST:
                    ui.showTaskList(tasks);
                    break;
                case DELETE:
                    int delTaskNum = Integer.parseInt(instruction.getParameter());
                    Task toBeDeleted = tasks.getTask(delTaskNum);
                    tasks.deleteFromTasks(delTaskNum);
                    ui.showDeletedTask(tasks, toBeDeleted);
                    break;
                case FIND:
                    String searchTerm = instruction.getParameter();
                    ui.showFoundTasks(tasks.findTasks(searchTerm));
                    break;
                default:
                    ;
                }

            } catch (DukeException e) {
                ui.showError(e);
                System.out.print("> ");
            }
        }
        storage.update(tasks);
        ui.showExitMessage();
    }
}
