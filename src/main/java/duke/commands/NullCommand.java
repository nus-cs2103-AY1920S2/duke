package duke.commands;

import java.util.List;
import duke.tasks.Task;

class NullCommand implements Command {
    public void execute(String arg, List<Task> tasks) {
        System.out.print(formatReply("Command not found!"));
    }
}