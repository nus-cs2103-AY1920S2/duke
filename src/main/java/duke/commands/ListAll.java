package duke.commands;

import java.util.List;
import duke.tasks.Task;

class ListAll implements Command {
    public void execute(String arg, List<Task> tasks) {
        int counter = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (Task task : tasks) {
            sb.append(counter);
            sb.append(".");
            sb.append(task);
            sb.append("\n");
            counter += 1;
        }
        System.out.print(formatReply(sb.toString()));
    }
}