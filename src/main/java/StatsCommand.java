import java.util.List;

public class StatsCommand extends Command {

    public StatsCommand(TaskList taskList) {
        super(taskList);
    }

    public String execute() {
        StringBuilder output;
        output = new StringBuilder("Here are your statistics so far for this task list:\n");
        output.append("Total number of successful input commands: " + stats.size() + "\n\n");
        int i = 1;
        output.append("All successful input commands so far:\n");
        for (Command c : stats) {
            output.append("    " + i + ". " + c.toString() + "\n");
            i++;
        }
        return output.toString();
    }

}