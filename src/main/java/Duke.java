
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import sampletest.Task;
import sampletest.Events;
import sampletest.Deadlines;
import java.util.ArrayList;
import java.util.Collections;
import exception.DukeException;
import exception.ExceptionGenerator;

/**
 * CS2103 Individual Project.
 * The class for the ChatBot.
 * @author Wei Cheng
 */

public class Duke  {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    /**
     * Constructor to create an instance of Duke.
     *
     * @param filePath the String representation of the file location
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * To run the conversation with the user.
     */
    public String run(String input) {
        String[] userInput = input.split(" ", 2);
        Parser parser = new Parser(userInput);
        String command = parser.getCommand();
        try {
            ExceptionGenerator.checkInputLength(userInput);
            ExceptionGenerator.checkInputAction(userInput);
            switch (command) {
            case "done":
                int taskNo = Integer.parseInt(parser.getDescription());
                this.tasks.getTask(taskNo - 1).markAsDone();
                Integer numbOfTask = this.tasks.taskStorage.size();
                return ("Nice! I've marked this task as done:\n "
                        + this.tasks.getTask(taskNo - 1).toString()
                        + "\nNow you have " + numbOfTask.toString() + " tasks in the list.");
            case "delete":
                taskNo = Integer.parseInt(userInput[1]) - 1;
                Task removedTask = this.tasks.getTask(taskNo);
                this.tasks.removeTask(taskNo);
                numbOfTask = this.tasks.taskStorage.size();
                return ("Noted. I've removed this task:\n"
                        + removedTask.toString()
                        + "\nNow you have " + numbOfTask.toString() + " tasks in the list.");
                //break;
            case "list":
                numbOfTask = this.tasks.taskStorage.size();
                return this.printText() + ("\nNow you have " + numbOfTask.toString() + " tasks in the list.");
            case "todo":
                String description = parser.getDescription();
                Task task = new Task(description);
                this.tasks.add(task);
                numbOfTask = this.tasks.taskStorage.size();
                return ("Got it. I've added this task:\n" + task.toString()
                        + "\nNow you have " + numbOfTask + " tasks in the list.");
                //break;
            case "deadline":
                String[] tokens = parser.getDescription().split(" /by ");
                ExceptionGenerator.checkDeadlinesInput(tokens);
                Deadlines deadlines = new Deadlines(tokens[0], tokens[1]);
                this.tasks.add(deadlines);
                numbOfTask = this.tasks.taskStorage.size();
                return ("Got it. I've added this task:\n" + (deadlines.getDate()).toString()
                        + "\nNow you have " + numbOfTask.toString() + " tasks in the list.");
            case "event":
                tokens = parser.getDescription().split(" /at ");
                ExceptionGenerator.checkEventInput(tokens);
                Events event = new Events(tokens[0], tokens[1]);
                this.tasks.add(event);
                numbOfTask = this.tasks.taskStorage.size();
                return ("Got it. I've added this task:\n" + event.toString()
                        + "\nNow you have " + numbOfTask.toString() + " tasks in the list.");
            case "sort":
                Collections.sort(this.tasks.taskStorage);
                numbOfTask = this.tasks.taskStorage.size();
                return this.printText() + ("\nNow you have " + numbOfTask.toString() + " tasks in the list.");

            case "find":
                String keyWord = parser.getDescription();
                ArrayList<Task> matchingTasks = new ArrayList<>();
                for (int i = 0; i < tasks.taskStorage.size(); i++) {
                    task = tasks.taskStorage.get(i);
                    if ((task.getDescription()).contains(keyWord)) {
                        matchingTasks.add(task);
                    }
                }
                if (matchingTasks.size() == 0) {
                    return ("There is no matching task in your list");
                } else {
                    StringBuilder taskInformation = new StringBuilder("Here are the matching tasks in your list:");
                    for (Task t : matchingTasks) {
                        taskInformation.append(t.toString());
                    }
                    return taskInformation.toString();
                }
            default:
                return Ui.initiateFareWell();
            }
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }

    private void updateData(ArrayList<Task> taskStorage) {
        storage.saveToDisk(this.tasks.taskStorage);
    }

    /**
     * To print out all the task in the
     * TaskStorage.
     */
    public String printText() {
        int counter = 1;
        StringBuilder text = new StringBuilder();
        for (Task task : this.tasks.taskStorage) {
            text.append(Integer.valueOf(counter).toString() + "." + task.toString() + "\n");
            counter++;
        }
        return text.toString();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

}



