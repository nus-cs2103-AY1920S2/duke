package duke.commands;

import java.util.HashMap;

import duke.ui.Ui;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.parsers.DateTimeParser;
import duke.exceptions.DukeException;

/**
 * Parses user-entered strings and executes them as Commands.
 * Remains active until user deactivates with "bye"
 */
public class CommandHandler {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private HashMap<String, Command> commands;
    private boolean isActive;

    public CommandHandler(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        this.commands = new HashMap<>();
        isActive = true;

        // Create parsers
        DateTimeParser dtParser = new DateTimeParser();

        // Register commands
        commands.put("list", new ListAll());
        commands.put("done", new MarkTaskAsDone());
        commands.put("todo", new CreateTodo());
        commands.put("deadline", new CreateDeadline(dtParser));
        commands.put("event", new CreateEvent(dtParser));
        commands.put("delete", new DeleteTask());
        commands.put("find", new FindTasks());
    }

    /**
     * Parses the user string into a Command and executes it.
     *
     * @param cmd User-entered string to be parsed.
     */
    public void executeCmd(String cmd) {
        String cmdWord;
        String arg;

        // Check if command is bye
        if (cmd.equalsIgnoreCase("bye")) {
            isActive = false;
            return;
        }

        // Parse other types of commands
        int spaceIndex = cmd.indexOf(" ");
        if (spaceIndex == -1) {
            // No spaces found, so must be single-word command
            cmdWord = cmd.toLowerCase();
            arg = "";
        } else {
            // Get first word from the cmd string
            cmdWord = cmd.substring(0, spaceIndex).toLowerCase();
            arg = cmd.substring(spaceIndex + 1);
        }

        // Execute parsed command
        try {
            commands.get(cmdWord).execute(arg, tasks, ui, storage);
        } catch (NullPointerException e) {
            ui.showError("Command " + cmdWord + " does not exist!");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isActive() {
        return isActive;
    }
}