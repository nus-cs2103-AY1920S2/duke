import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Main class that drives the code to run the Duke bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("images/DaDuke.png"));
    private static final String filepath = "data/duke.txt";


    /**
     * Duke class constructor for JavaFX.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.readFile());
        Scanner sc = new Scanner(System.in);
        parser = new Parser(sc);
    }

    /**
     * Duke class constructor that creates a new instance of a Duke bot.
     *
     * @param filePath Path of file for instance of Storage class to be created.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readFile());
        Scanner sc = new Scanner(System.in);
        parser = new Parser(sc);
    }

    /**
     * Main method that drives the Duke bot, using Command Line.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(sc);

        String command = parser.readCommand();

        try {
            while (!parser.getCommandType(command).equals("bye")) {

                String commandType = parser.getCommandType(command);

                switch (commandType) {
                case "list":
                    tasks.printList();
                    command = parser.readCommand();
                    break;
                case "find":
                    String findString = parser.getFind(command);
                    TaskList matchedTask = tasks.find(findString);
                    ui.printFind(matchedTask);
                    command = parser.readCommand();
                    break;
                case "done": {
                    int taskNo = parser.getTaskNo(command);
                    try {
                        tasks.setDone(taskNo);
                        ui.printDone(tasks.getTask(taskNo));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("There is no task " + (taskNo + 1) + " in the list.");
                    }
                    command = parser.readCommand();
                    break;
                }
                case "delete": {
                    int taskNo = parser.getTaskNo(command);
                    Task task = tasks.getTask(taskNo);
                    ui.printDelete(task, tasks.getSize() - 1);
                    tasks.deleteTask(taskNo);
                    storage.saveFile(tasks.getTaskList());
                    command = parser.readCommand();
                    break;
                }
                case "deadline":
                    try {
                        Deadline deadline = parser.getDeadline(command);
                        tasks.addTask(deadline);
                        storage.saveFile(tasks.getTaskList());
                        ui.printAdd(deadline, tasks.getSize());
                    } catch (DukeException | ParseException e) {
                        System.out.println(e.getMessage());
                    }
                    command = parser.readCommand();
                    break;
                case "event":
                    try {
                        Event event = parser.getEvent(command);
                        tasks.addTask(event);
                        storage.saveFile(tasks.getTaskList());
                        ui.printAdd(event, tasks.getSize());
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    command = parser.readCommand();
                    break;
                case "todo":
                    try {
                        ToDo toDo = parser.getToDo(command);
                        tasks.addTask(toDo);
                        storage.saveFile(tasks.getTaskList());
                        ui.printAdd(toDo, tasks.getSize());
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    command = parser.readCommand();
                    break;
                default:
                    System.out.println(new DukeException("Invalid command"));
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            command = parser.readCommand();
        }

        ui.printBye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
    * Gets the response of Duke based on the command given from the user.
     * @param command String input from the user.
     */
    protected String getResponse(String command) {
        try {
            String commandType = parser.getCommandType(command);
            switch (commandType) {
            case "list":
                return tasks.printList();
            case "find":
                String findString = parser.getFind(command);
                TaskList matchedTask = tasks.find(findString);
                return ui.printFind(matchedTask);
            case "done": {
                int taskNo = parser.getTaskNo(command);
                try {
                    tasks.setDone(taskNo);
                    return ui.printDone(tasks.getTask(taskNo));
                } catch (IndexOutOfBoundsException e) {
                    return ("There is no task " + (taskNo + 1) + " in the list.");
                }
            }
            case "delete": {
                int taskNo = parser.getTaskNo(command);
                Task task = tasks.getTask(taskNo);
                tasks.deleteTask(taskNo);
                storage.saveFile(tasks.getTaskList());
                return ui.printDelete(task, tasks.getSize());
            }
            case "deadline":
                try {
                    Deadline deadline = parser.getDeadline(command);
                    tasks.addTask(deadline);
                    storage.saveFile(tasks.getTaskList());
                    return ui.printAdd(deadline, tasks.getSize());
                } catch (DukeException | ParseException e) {
                    return (e.getMessage());
                }
            case "event":
                try {
                    Event event = parser.getEvent(command);
                    tasks.addTask(event);
                    storage.saveFile(tasks.getTaskList());
                    return ui.printAdd(event, tasks.getSize());
                } catch (DukeException e) {
                    return (e.getMessage());
                }
            case "todo":
                try {
                    ToDo toDo = parser.getToDo(command);
                    tasks.addTask(toDo);
                    storage.saveFile(tasks.getTaskList());
                    return ui.printAdd(toDo, tasks.getSize());
                } catch (DukeException e) {
                    return (e.getMessage());
                }
            case "bye":
                return ui.printBye();
            case "snooze":
                int taskNo = parser.getTaskNo(command);
                try {
                    int noDays = parser.getSnoozeNo(command);
                    assert tasks.getTask(taskNo) != null : "Tasklist contains valid tasks.";
                    if (tasks.getTask(taskNo) instanceof ToDo) {
                        throw new DukeException("ToDo cannot be snoozed.");
                    } else if (tasks.getTask(taskNo) instanceof Deadline) {
                        Deadline deadline = (Deadline) tasks.getTask(taskNo);
                        deadline.snooze(noDays);
                        return ui.printSnooze(deadline, noDays);
                    } else {
                        Event event = (Event) tasks.getTask(taskNo);
                        event.snooze(noDays);
                        return ui.printSnooze(event, noDays);
                    }
                } catch (DukeException e) {
                    return e.getMessage();
                }
            default:
                return "Invalid command, please try again.";
            }
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }
}
