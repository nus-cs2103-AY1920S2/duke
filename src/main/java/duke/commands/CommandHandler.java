package duke.commands;

import java.util.HashMap;

import duke.ui.Ui;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.parsers.CommandParser;
import duke.exceptions.DukeException;

/**
 * Parses user-entered strings and executes them as Commands. Remains active
 * until user deactivates with "bye"
 */
public class CommandHandler {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private HashMap<String, Command> commands;
    private CommandParser commandParser;
    private boolean isActive;

    /**
     * Creates a CommandHandler.
     * 
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Persistent storage.
     */
    public CommandHandler(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        this.commands = new HashMap<>();
        this.commandParser = new CommandParser();
        isActive = true;

        // Register commands
        commands.put("list", new ListAll(commandParser));
        commands.put("done", new MarkTaskAsDone(commandParser));
        commands.put("todo", new CreateTodo(commandParser));
        commands.put("deadline", new CreateDeadline(commandParser));
        commands.put("event", new CreateEvent(commandParser));
        commands.put("delete", new DeleteTask(commandParser));
        commands.put("find", new FindTasks(commandParser));
        commands.put("reschedule", new RescheduleTask(commandParser));
    }

    public boolean isActive() {
        return isActive;
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
        String[] commandAndArg = commandParser.splitByDelimiter(command, " ");
        String commandWord = commandAndArg[0].toLowerCase().strip();

        // Execute command
        if (commandAndArg.length < 2) {
            // No args provided
            executeCommand(commandWord, "");
        } else {
            // Use provided args
            executeCommand(commandWord, commandAndArg[1]);
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