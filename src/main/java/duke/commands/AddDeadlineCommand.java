package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.TaskList;

import java.util.List;

public class AddDeadlineCommand implements Command {
    private TaskList taskList;
    private Deadline deadline;

    /**
     * Creates an AddDeadlineCommand that adds a new Deadline to the tasklist.
     * @param taskList List of all the tasks saved by the user.
     * @param details List of description, time and date of deadline.
     */
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
