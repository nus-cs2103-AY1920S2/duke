//Code for GUI is heavily adapted from the Duke JavaFX tutorials, available at
//https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart1.md

package duke.ui;

import duke.Duke;
import duke.tasks.Task;
import duke.exceptions.DukeException;
import duke.util.PrintUtil;

import java.util.ArrayList;
import java.io.IOException;
import java.lang.UnsupportedOperationException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXMLLoader;


/**
 * Encapsulates a graphical user interface (GUI) frontend of Duke.
 * The `Gui` class implements a GUI which allows the user to enter commands and view its responses.
 * The task list is saved when the user enters the "bye" command,
 * or when the user closes the window.
 */
public class Gui implements Ui {
    private Stage stage;
    private MainWindow mainWindow;
    private Duke duke;
    private boolean isClosed = false;
    private ArrayList<Message> messageBuffer;
    
    /**
     * Constructs a new `Gui` instance.
     * Each `Duke` object should construct at most one `Gui` instance using itself as the parameter.
     * @param duke The corresponding `Duke` instance
     */
    public Gui(Duke duke) {
        this.duke = duke;
        messageBuffer = new ArrayList<>();
        
        PrintUtil.setIndentLevel(0);
    }
    
    /**
     * Receives control of the JavaFX `Application` from `Duke`.
     * Sets up GUI components and callbacks, then runs the event loop until termination.
     * @param stage `Stage` object received from `Duke#start`
     */
    public void start(Stage stage) {
        this.stage = stage;
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
            mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setGui(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //Step 3. Add functionality to handle user input.
        startMessage();
        showGreeting();
        messageBuffer.add(new Message(endMessage()));
        
        //Finally, add pending messages to the `dialogContainer`.
        processMessageBuffer();
    }
    
    /**
     * Denotes the start of a new message to be printed.
     */
    public void startMessage() {
    }
    
    /**
     * Denotes the end of a new message to be printed.
     * @return Completed message
     */
    public String endMessage() {
        String buffer = PrintUtil.flushBuffer();
        return String.format("%s", buffer);
    }

    protected String getResponse(String input) {
        return duke.processCommand(input);
    }

    private void processMessageBuffer() {
        for (Message m : messageBuffer) {
            mainWindow.displayDukeMessage(m.text);
        }
        messageBuffer.clear();
    }

    /**
     * Reads a single-line command string from the user.
     * @return command string
     */
    public String readCommandString() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        PrintUtil.printHeaderLine();
    }
    
    /**
     * Displays a numbered task.
     * @param index Index of the task
     * @param task Task to be displayed
     */
    public void showNumberedEntry(int index, Task task) {
        PrintUtil.indentedPrintf("%d.%s\n", index, task);
    }
    
    /**
     * Displays the message of the provided `DukeException`.
     * @param e exception of type DukeException
     */
    public void showError(DukeException e) {
        PrintUtil.indentedPrintf("Error: %s\n",e.getMessage());
    }
    
    /**
     * Displays a message that the task list could not be found.
     * @param savePath Intended path of the file
     */
    public void showSaveNotFoundMessage(String savePath) {
        PrintUtil.printHeaderLine();
        PrintUtil.indentedPrintln("Error: Task list not found");
        PrintUtil.indentedPrintf("       Duke will create a new task list file at %s\n", savePath);
        PrintUtil.printHeaderLine();
    }
    
    /**
     * Displays a greeting.
     */
    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        PrintUtil.indentedPrintln("Hello from\n" + logo);
    }
    
    /**
     * Displays the goodbye message.
     * Header lines are not printed.
     */
    public void showBye() {
        PrintUtil.indentedPrintln("Goodbye");
    }
    
    /**
     * Displays a message when a task is added.
     * @param task Task added
     * @param remainingCount New number of tasks
     */
    public void showAddTaskMessage(Task task, int remainingCount) {
        PrintUtil.indentedPrintf("Added task:\n  %s\n", task);
        PrintUtil.indentedPrintf("Now you have %d task(s).\n", remainingCount);
    }
    
    /**
     * Displays a message when a task is removed.
     * @param task Task added
     * @param remainingCount New number of tasks
     */
    public void showRemoveTaskMessage(Task task, int remainingCount) {
        PrintUtil.indentedPrintf("Removed task:\n  %s\n", task);
        PrintUtil.indentedPrintf("Now you have %d task(s).\n", remainingCount);
    }
    
    /**
     * Displays a message when a task is marked as completed.
     * @param task Task marked as complete
     */
    public void showDoneTaskMessage(Task task) {
        PrintUtil.indentedPrintf("Marked task as done:\n  %s\n", task);
    }
    
    /**
     * Displays an error message when a command is not recognized.
     * @param command Command string
     */
    public void showUnknownCommandMessage(String command) {
        PrintUtil.indentedPrintf("Error: Unknown command: %s\n", command);
    }
    
    /**
     * Displays a header message to indicate matching tasks from a query.
     * This method only prints the header message.
     */
    public void showMatchingTasksMessage() {
        PrintUtil.indentedPrintln("Here are the matching tasks in your list:");
    }
    
    /**
     * Displays a header message when tasks are sorted.
     * This method only prints the header message.
     */
    public void showSortMessage() {
        PrintUtil.indentedPrintln("Sorted tasks by date. Here are your tasks:");
    }
    
    /**
     * Displays a help command for a given message name.
     * @param commandName Command name
     */
    public void showHelpMessage(String commandName) {
        switch (commandName) {
        case "bye":
            PrintUtil.indentedPrintln("Usage: bye");
            PrintUtil.indentedPrintln("Saves task list to file and exits.");
            break;
        case "deadline":
            PrintUtil.indentedPrintln("Usage: deadline [task description] /by [yyyy-mm-dd]");
            PrintUtil.indentedPrintln("Adds a new Deadline task into the task list.");
            break;
        case "delete":
            PrintUtil.indentedPrintln("Usage: delete [i]");
            PrintUtil.indentedPrintln("Removes the i-th task from the task list.");
            break;
        case "done":
            PrintUtil.indentedPrintln("Usage: done [i]");
            PrintUtil.indentedPrintln("Marks the i-th task in the task list as done.");
            break;
        case "event":
            PrintUtil.indentedPrintln("Usage: event [task description] /at [yyyy-mm-dd]");
            PrintUtil.indentedPrintln("Adds a new Event task into the task list.");
            break;
        case "find":
            PrintUtil.indentedPrintln("Usage: find [task description]");
            PrintUtil.indentedPrintln("Finds and displays tasks whose description matches the query string.");
            break;
        case "list":
            PrintUtil.indentedPrintln("Usage: list");
            PrintUtil.indentedPrintln("Displays the tasks in the task list.");
            break;
        case "sort":
            PrintUtil.indentedPrintln("Usage: sort");
            PrintUtil.indentedPrintln("Sorts all tasks in chronological order, then lists the tasks.");
            break;
        case "todo":
            PrintUtil.indentedPrintln("Usage: todo [task description]");
            PrintUtil.indentedPrintln("Adds a new ToDo task into the task list.");
            break;
        default:
            PrintUtil.indentedPrintf("Unknown command \"%s\"\n", commandName);
        }
    }
    
    /**
     * Closes and cleans up resources held by the UI.
     * This method closes the only JavaFX `Stage`, thereby ending the GUI loop.
     */
    public void close() {
        if (!isClosed) {
            stage.close();
        }
        isClosed = true;
    }
}
