import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Represents main body for Duke to run.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image goose = new Image(this.getClass().getResourceAsStream("/images/DaGoose.png"));
//    private Circle clip = new Circle(50);

    /**
     * Constructor for Duke.
     *
     * @param listPath Relative file path for where the task list is stored
     * @param arrayPath Relative file path for where the task array is stored
     */
    public Duke(String listPath, String arrayPath) {
        ui = new Ui();
        storage = new Storage(listPath, arrayPath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {

    }

    /**
     * Sets up and runs Duke to begin accepting user commands. Send 'bye' to close the program.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;
        String input;
        while (!isBye) {
            input = scanner.nextLine();
            isBye = Parser.isBye(input);

            try {
                String command = Parser.parseCommand(input);
                assert !command.equalsIgnoreCase("bye");
                String[] inputArr = input.split(" ");
                try {
                    int taskIndex;
                    Task selected;

                    switch (command) {
                    case ("list"):
                        ui.showList(storage.loadList());
                        break;

                    case ("done"):
                        taskIndex = Integer.parseInt(inputArr[1]) - 1;
                        selected = tasks.getTaskList().get(taskIndex);
                        tasks.markDone(taskIndex);
                        ui.showDoneTask(selected);
                        storage.save(tasks.getTaskList());
                        break;

                    case ("delete"):
                        taskIndex = Integer.parseInt(inputArr[1]) - 1;
                        selected = tasks.getTaskList().get(taskIndex);
                        tasks.deleteTask(taskIndex);
                        ui.showDeleteTask(selected, tasks.getTaskList());
                        storage.save(tasks.getTaskList());
                        break;

                    case ("find"):
                        String search = "";
                        for (int i = 1; i < inputArr.length; i++) {
                            search += inputArr[i];
                            search += (i == inputArr.length - 1) ? "" : " ";
                        }
                        ArrayList<Task> foundTasks = tasks.findTask(search);
                        ui.showFoundTasks(foundTasks);
                        break;

                    case ("todo"):
                        Todo addedTodo = tasks.createTodo(inputArr);
                        ui.showAddTask(addedTodo, tasks.getTaskList());
                        storage.save(tasks.getTaskList());
                        break;

                    case ("event"):
                        Event addedEvent = tasks.createEvent(input);
                        ui.showAddTask(addedEvent, tasks.getTaskList());
                        storage.save(tasks.getTaskList());
                        break;

                    case ("deadline"):
                        Deadline addedDeadline = tasks.createDeadline(input);
                        ui.showAddTask(addedDeadline, tasks.getTaskList());
                        storage.save(tasks.getTaskList());
                        break;

                    default:
                        ui.showError("Honk! Something went wrong.");
                    }
                } catch (IOException e) {
                    ui.showError("Honk! Something went wrong.");
                } catch (GooseTaskExistenceException | GooseEmptyDescriptionException
                        | GooseIllegalFormatException e) {
                    ui.showError(e.getMessage());
                }
            } catch (GooseUnrecognisedException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showBye();
    }

    @Override
    public void start(Stage stage) {
    }

    public static void main(String[] args) {
        new Duke("duke.txt", "mainList.txt").run();
    }
}
