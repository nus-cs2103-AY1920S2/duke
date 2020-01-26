package duke.commands;

import java.util.HashMap;

import duke.ui.Ui;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;

public class CommandHandler {
    protected TaskList tasks;
    protected HashMap<String, Command> commands;
    protected boolean isActive;
    protected Ui ui;

    public CommandHandler(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
        this.commands = new HashMap<>();
        isActive = true;

        // Register commands
        commands.put("list", new ListAll());
        commands.put("done", new MarkTaskAsDone());
        commands.put("todo", new CreateTodo());
        commands.put("deadline", new CreateDeadline());
        commands.put("event", new CreateEvent());
        commands.put("delete", new DeleteTask());
    }

    public void executeCmd(String cmd) {
        // First parse command
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
            commands.get(cmdWord).execute(arg, tasks, ui);
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