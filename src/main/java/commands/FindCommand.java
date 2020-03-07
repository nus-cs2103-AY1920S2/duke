package commands;

import storage.Storage;
import task.Task;
import taskList.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String key;

    public FindCommand(String key) {
        this.key = key;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        try {
            ArrayList<Task> matchedTasks = new ArrayList<>();
            String output = "Search Results as follows: ";
            for (Task t : tasks.getList()) {
                if (t.contains(key)) {
                    matchedTasks.add(t);
                }
            }
            for (Task t : matchedTasks) {
                output += "\n" + t.toString();
            }
            return new CommandResult(output + "\n");
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult("Index does not exist");
        }
    }
}
