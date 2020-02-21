package Duke.command;

import Duke.TaskList;

import java.util.List;
import java.util.stream.Collectors;

public class StatsCommand extends Command {

    public StatsCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Executes stats command.
     * @return Statistics related to user input on add, done, and delete task operations on the task list.
     */
    public String execute() {
        StringBuilder output;
        List<Command> addStats = stats.stream().filter(p -> p instanceof AddCommand)
                .collect(Collectors.toList());
        List<Command> completedStats = stats.stream().filter(p -> p instanceof DoneCommand)
                .collect(Collectors.toList());
        List<Command> deletedStats = stats.stream().filter(p -> p instanceof DeleteCommand)
                .collect(Collectors.toList());
        output = new StringBuilder("Here are your statistics so far for this task list:\n");
        output.append("Total number of successful input commands: " + stats.size() + "\n");
        output.append("Total number of task added: " + addStats.size() + "\n");
        output.append("Total number of task completed: " + completedStats.size() + "\n");
        output.append("Total number of task deleted: " + deletedStats.size() + "\n\n");

        output.append("All successful input commands so far (old to new):\n");
        output.append(appendCommandString(stats));
        output.append("\nAll added tasks:\n");
        output.append(appendCommandString(addStats));
        output.append("\nAll completed tasks:\n");
        output.append(appendCommandString(completedStats));
        output.append("\nAll deleted tasks:\n");
        output.append(appendCommandString(deletedStats));
        return output.toString();
    }

    private StringBuilder appendCommandString(List<Command> commandList) {
        StringBuilder output = new StringBuilder();
        int i = 1;
        for (Command c : commandList) {
            output.append("    " + i + ". " + c.toString() + "\n");
            i++;
        }
        return output;
    }

}