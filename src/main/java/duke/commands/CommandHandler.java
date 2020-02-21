package duke.commands;

import java.util.HashMap;

import javafx.util.Pair;

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

    /**
     * Creates a CommandHandler.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Persistent storage.
     */
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
        commands.put("reschedule", new RescheduleTask(dtParser));
    }

    /**
     * Parses the user string into a Command and executes it.
     *
     * @param command User-entered string to be parsed.
     */
    public void handleCommand(String command) {
        // Check if command is bye
        if (command.equalsIgnoreCase("bye")) {
            isActive = false;
            return;
        }

        // Extract out command word and arguments
        Pair<String, String> commandAndArg = getCommandAndArg(command);
        String commandWord = commandAndArg.getKey();
        String arg = commandAndArg.getValue();

        // Execute command
        executeCommand(commandWord, arg);
    }

    public boolean isActive() {
        return isActive;
    }

    private Pair<String, String> getCommandAndArg(String str) {
        int spaceIndex = str.indexOf(" ");
        if (spaceIndex == -1) {
            // No spaces found, so must be single-word command
            return new Pair<String, String>(str.toLowerCase(), "");
        } else {
            // Split string into command word and arguments
            String commandWord = str.substring(0, spaceIndex).toLowerCase();
            String arg = str.substring(spaceIndex + 1);
            return new Pair<String, String>(commandWord, arg);
        }
    }

    private void executeCommand(String commandWord, String arg) {
        // Check if command exists
        if (!commands.containsKey(commandWord)) {
            ui.showError("Command " + commandWord + " does not exist!");
            return;
        }

        try {
            commands.get(commandWord).execute(arg, tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}