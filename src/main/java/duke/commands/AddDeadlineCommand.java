package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.TaskList;

import java.util.List;

public class AddDeadlineCommand implements Command{
    private TaskList taskList;
    private Deadline deadline;

    public AddDeadlineCommand(TaskList taskList, List<String> details) {
        Deadline deadline = new Deadline(details.get(0), details.get(1), details.get(2));
        this.taskList = taskList;
        this.deadline = deadline;
    }

    @Override
    public String execute() {
        taskList.add(deadline);
        return "Got it. I've added this deadline:\n"
                + deadline
                + "\nNow you have " + taskList.size() + " tasks on the list.";
    }
}
