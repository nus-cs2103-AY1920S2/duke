import exception.DukeException;
import exception.InvalidCommandException;
import exception.InvalidTaskIndexException;
import tasks.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Duke class contains the main method.
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

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    /**
     * Makes a response for a given command.
     * @param command The command provided by the user.
     * @return The response.
     * @throws IOException Throws IOException.
     */
    String getResponse(String command) throws IOException{
        String response;
        if (!command.equals("bye")) {
            try {
                response = handle(command);
            } catch (DukeException e) {
                response = e.getMessage();
            }
        } else {
            storage.saveData();
            System.exit(0);
            response = "";
        }
        return response;
    }

    /**
     * The constructor for the Duke class.
     */
    public Duke() throws IOException, DukeException {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage();
        parser = new Parser();
        storage.loadData();
    }

    /**
     * The main method for Duke.
     * @param args For the main method.
     * @throws IOException Throws IOException.
     */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke().run();
    }


    /**
     * Runs the duke program.
     * @throws IOException Throws IOException.
     */
    public void run() throws IOException {
        System.out.println(ui.welcomeMessage());
        System.out.println("What can I do for you");

        Scanner sc = new Scanner(System.in);
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            try {
                System.out.println(handle(command));
            }
            catch (DukeException ex) {
                ui.showError(ex.getMessage());
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String handle(String command) throws DukeException, IOException {
        String type = parser.getType(command);
        String response;
        switch (type) {
            case "list" :
                response = ui.printList();
                break;
            case "done" :
                int taskNum = parser.getTaskIndex(command);
                if (taskNum > tasks.numOfTasks() || taskNum <= 0) {
                    throw new InvalidTaskIndexException(":-( Oops!! Not a valid number :-(");
                } else {
                    tasks.getTask(taskNum - 1).markAsDone();
                    response = ui.doneMessage(tasks.getTask(taskNum - 1));
                    storage.doneTask(taskNum - 1);
                }
                break;
            case "delete" :
                int taskNo = parser.getTaskIndex(command);
                if (taskNo > tasks.numOfTasks() || taskNo <= 0) {
                    throw new InvalidTaskIndexException(":-( Oops!! Not a valid number :-(");
                } else {
                    response = ui.deleteMessage(tasks.getTask(taskNo - 1));
                    tasks.removeTask(taskNo - 1);
                    storage.deleteTask(taskNo - 1);
                }
                break;
            case "find" :
                ArrayList<Integer> taskFound = new ArrayList<>();
                for (int i = 0; i < tasks.numOfTasks(); i++) {
                    if (tasks.getTask(i).getDescription().contains(command.split(" ")[1])) {
                        taskFound.add(i);
                    }
                }
                response = ui.printSelected(taskFound);
                break;
            case "todo" :
            case "event" :
            case "deadline" :
                Task task = tasks.createAndAddTask(type, command);
                response = ui.addMessage(task);
                storage.saveNewTask(task);
                break;
            default :
                // invalid command
                throw new InvalidCommandException(":-( Oops!!! I'm sorry, but I don't know what that means :-(");
        }
        return response;
    }
}
