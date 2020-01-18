package duke.commands;

import java.util.List;
import java.util.HashMap;
import duke.tasks.Task;
import duke.exceptions.DukeException;

public class CommandHandler {
    protected List<Task> tasks;
    protected HashMap<String, Command> commands;
    protected boolean isActive;

    public CommandHandler(List<Task> tasks) {
        this.tasks = tasks;
        this.commands = new HashMap<>();
        // Register commands
        commands.put("list", new ListAll());
        commands.put("done", new MarkTaskAsDone());
        commands.put("todo", new CreateTodo());
        commands.put("deadline", new CreateDeadline());
        commands.put("event", new CreateEvent());
        isActive = true;
    }

    public void executeCmd(String cmd) {
        String cmdWord, arg;
        // Check if command is bye
        if (cmd.equalsIgnoreCase("bye")) {
            isActive = false;
            return;
        }
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
        try {
            commands.get(cmdWord).execute(arg, tasks);
        } catch (NullPointerException e) {
            System.out.print(formatReply("Command " + cmdWord + " does not exist!"));
        } catch (DukeException e) {
            System.out.print(formatReply(e.getMessage()));
        }
    }

    public boolean isActive() {
        return isActive;
    }

    private String formatReply(String str) {
        String[] lines = str.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        String lineBreak = "===========================================================\n";
        for (String line : lines) {
            sb.append("> ");
            sb.append(line);
            sb.append("\n");
        }
        sb.append(lineBreak);
        return sb.toString();
    }
}