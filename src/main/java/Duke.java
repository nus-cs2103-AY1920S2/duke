import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static Scanner lineScanner;
    public static Scanner sc;

    private final String FILE_NAME = "./data/duke.txt";
    private Ui ui = new Ui();
    private Storage storage = new Storage(FILE_NAME);
    private TaskList tasks = new TaskList(storage);

    // Empty constructor required for Launcher
    public Duke() {
    }


    public static void main(String[] args) throws FileNotFoundException {
        new Duke().run();
    }

    private void run() {

        ui.showWelcomeMessage();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled
     */
    private Label getDialogLabel(String text) {
        // need to import javafx.scene.control.label
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userInput) {
        if (userInput.equals("bye")) {
            return ui.showGoodbyeMessage();
        } else if (userInput.equals("list")) {
            return tasks.printTasks();
        } else if (userInput.startsWith("done")) {
            tasks.makeTaskDone(-1 + Integer.parseInt(userInput.split(" ")[1]));
        } else if (userInput.startsWith("todo")) {
            lineScanner = new Scanner(userInput);
            lineScanner.next(); // skip todo word
            try {
                Task task = new Todo(lineScanner.nextLine().substring(1)); // skip space
                tasks.addTask(task);
                storage.saveAllTasksToFile(tasks);
            } catch (NoSuchElementException | FileNotFoundException error) {
                ui.showEmptyDescription("todo");
            }
        } else if (userInput.startsWith("deadline")) {
            lineScanner = new Scanner(userInput);
            lineScanner.next();
            String theRest = lineScanner.nextLine();
            try {
                Task task = new Deadline(theRest.split("/")[0].substring(1), theRest.split("/")[1]);
                tasks.addTask(task);
                storage.saveAllTasksToFile(tasks);
            } catch (NoSuchElementException | FileNotFoundException error) {
                ui.showEmptyDescription("deadline");
            }
        } else if (userInput.startsWith("event")) {
            lineScanner = new Scanner(userInput);
            lineScanner.next();
            String theRest = lineScanner.nextLine();
            try {
                Task task = new Event(theRest.split("/")[0].substring(1), theRest.split("/")[1]);
                tasks.addTask(task);
                storage.saveAllTasksToFile(tasks);
            } catch (NoSuchElementException | FileNotFoundException error) {
                ui.showEmptyDescription("event");
            }
        } else if (userInput.startsWith("delete")) {
            try {
                int taskNumber = -1 + Integer.parseInt(userInput.split(" ")[1]);
                tasks.getTasks().get(taskNumber); // try to get exception
                tasks.deleteTask(taskNumber);
            } catch (IndexOutOfBoundsException error) {
                ui.showInvalidRemoval();
            }
        } else if (userInput.startsWith("filter")) { //filter {date/month/year} {value}
            try {
                String criterion = userInput.split(" ")[1];
                if (criterion.equals("month")) {
                    int month = Integer.parseInt(userInput.split(" ")[2]);
                    tasks.showFilteredBySpecificMonth(month);
                } else if (criterion.equals("year")) {
                    int year = Integer.parseInt(userInput.split(" ")[2]);
                    tasks.showFilteredBySpecificYear(year);
                } else if (criterion.equals("date")) {
                    String date = userInput.split(" ")[2];
                    tasks.showFilteredBySpecificDate(date);
                }
            } catch (NoSuchElementException e) {
                ui.showMissingParemeters();
                ui.showLineOfUnderscores();
            }
        } else if (userInput.startsWith("find")) {
            try {
                String word = userInput.split(" ")[1];
                tasks.showFilteredByName(word);
            } catch (NoSuchElementException e) {
                ui.showMissingParemeters();
            }
        } else {
            ui.showCommandNotFound();
            ui.showLineOfUnderscores();
        }
        return "d";
    }
}