package duke;

import duke.commands.Command;
import duke.commands.exceptions.CommandException;
import duke.model.Storage;
import duke.model.TaskList;
import duke.model.Ui;
import duke.tasks.Task;
import duke.utils.CommandParser;
import duke.utils.DataParser;

import java.io.IOException;
import java.util.List;

/**
 * The <code>Duke</code> class is the entry point of the program.
 *
 * @author Zhu Yijie
 */
public class Duke {
    private static final String FILE_NAME = "duke.txt";
    private static final String FILE_DIRECTORY = "./data/";
    
    private TaskList taskList;
    private Storage storage;
    
    /**
     * Creates and initialises a Duke object.
     */
    public Duke() {
        Ui.sayHello();
        this.storage = new Storage(FILE_NAME, FILE_DIRECTORY);
        try {
            List<Task> tasks = DataParser.parseToTasks(this.storage.load());
            this.taskList = new TaskList(tasks);
            Ui.print("Task list loaded from storage. Enter `list` to see your tasks.");
        } catch (IOException | NullPointerException e) {
            Ui.print("Failed to get tasks from storage. Starting with an empty list.");
            this.taskList = new TaskList();
        }
    }
    
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = CommandParser.parseToCommand(input);
            return command.execute(taskList, storage);
        } catch (CommandException e) {
            return e.getMessage();
        }
    }
}
