import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    private Ui ui = new Ui();

    private Storage storage = new Storage();

    private TaskList tasks = new TaskList(storage);

    private static final int LENGTH_DATE = 10;

    // Empty constructor required for Launcher
    public Duke() throws IOException {
    }


    public static void main(String[] args) throws FileNotFoundException, IOException {
        new Duke().run();
    }

    private void run() {
        ui.showWelcomeMessage();
    }

    private Label getDialogLabel(String text) {
        // need to import javafx.scene.control.label
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Gets the response from Duke bot.
     */
    public String getResponse(String userInput) {
        if (userInput.equals("bye")) {
            return ui.showGoodbyeMessage();
        } else if (userInput.equals("list")) {
            return tasks.printTasks();
        } else if (userInput.startsWith("done")) {
            try {
                int position = -1 + Integer.parseInt(userInput.split(" ")[1]);
                assert (position < tasks.getTasksLength()); // out of bounds
                tasks.makeTaskDone(position);
                storage.saveAllTasksToFile(tasks);
            } catch (NumberFormatException error) {
                return "The task number you chose to delete is not valid"; // task index is not numeric
            } catch (FileNotFoundException error) {
                return "Database error. Please try again later!";
            }
            return "I have marked that task as done!";
        } else if (userInput.startsWith("todo")) {
            lineScanner = new Scanner(userInput);
            lineScanner.next(); // skip todo word

            try {
                String title = lineScanner.nextLine().substring(1);
                Task task = new Todo(title);
                tasks.addTask(task);
                storage.saveAllTasksToFile(tasks);
            } catch (NoSuchElementException error) {
                return ui.showEmptyDescription("todo");
            } catch (FileNotFoundException error) {
                return "Database error. Please try again later!";
            }
            return "I have added that todo!";
        } else if (userInput.startsWith("deadline")) {
            lineScanner = new Scanner(userInput);
            lineScanner.next();
            String theRest = lineScanner.nextLine();
            try {
                String title = theRest.split("/")[0].substring(1).trim();
                String date = theRest.split("/")[1].trim();

                // date must be in format YYYY-MM-DD
                assert (date.length() == LENGTH_DATE && date.charAt(4) == '-' && date.charAt(7) == '-');
                Task task = new Deadline(title, date);
                tasks.addTask(task);
                storage.saveAllTasksToFile(tasks);
                return "I have added that deadline!";
            } catch (NoSuchElementException error) {
                return ui.showEmptyDescription("deadline");
            } catch (FileNotFoundException error) {
                return "Database error. Please try again later!";
            }
        } else if (userInput.startsWith("event")) {
            lineScanner = new Scanner(userInput);
            lineScanner.next();
            String theRest = lineScanner.nextLine();
            try {
                String title = theRest.split("/")[0].substring(1).trim();
                String date = theRest.split("/")[1].trim();

                // date must be in format YYYY-MM-DD
                assert (date.length() == LENGTH_DATE && date.charAt(4) == '-' && date.charAt(7) == '-');
                Task task = new Event(title, date);
                tasks.addTask(task);
                storage.saveAllTasksToFile(tasks);
                return "I have added that event!";
            } catch (NoSuchElementException error) {
                return ui.showEmptyDescription("event");
            } catch (FileNotFoundException error) {
                return "Database error. Please try again later!";
            }
        } else if (userInput.startsWith("delete")) {
            try {
                int taskNumber = -1 + Integer.parseInt(userInput.split(" ")[1]);
                tasks.getTasks().get(taskNumber); // try to get exception
                tasks.deleteTask(taskNumber);
                return "I have deleted your task!";
            } catch (IndexOutOfBoundsException error) {
                return ui.showInvalidRemoval();
            }
        } else if (userInput.startsWith("filter")) { //filter {date/month/year} {value}
            try {
                String criterion = userInput.split(" ")[1];
                assert (criterion.equals("month") || criterion.equals("year") || criterion.equals("date"));
                if (criterion.equals("month")) {
                    int month = Integer.parseInt(userInput.split(" ")[2]);
                    return tasks.showFilteredBySpecificMonth(month);
                } else if (criterion.equals("year")) {
                    int year = Integer.parseInt(userInput.split(" ")[2]);
                    return tasks.showFilteredBySpecificYear(year);
                } else if (criterion.equals("date")) {
                    String date = userInput.split(" ")[2];
                    return tasks.showFilteredBySpecificDate(date);
                }
            } catch (NoSuchElementException e) {
                return ui.showMissingParemeters() + "\n" + ui.showLineOfUnderscores();
            }
        } else if (userInput.startsWith("find")) {
            try {
                String word = userInput.split(" ")[1];
                return tasks.showFilteredByName(word);
            } catch (NoSuchElementException e) {
                return ui.showMissingParemeters();
            }
        } else if (userInput.startsWith("sort deadlines")) {
            return tasks.sortDeadlinesByTime();
        } else if (userInput.startsWith("blah")) {
            final String BLAH = "blah";
            return BLAH;
        } else {
            return ui.showCommandNotFound();
        }
        return "I don't understand what you are saying";
    }
}