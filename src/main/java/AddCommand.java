import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class AddCommand extends Command {
    Task taskAdded;

    public AddCommand(TaskList taskList, Task newTask) {
        super(taskList);
        this.taskAdded = newTask;
    }

    public String execute() {
        String out;
            if (Objects.isNull(taskAdded)) {
                out = "Attempting to add invalid task. Operation aborted.";
            } else {
                list.add(taskAdded);
                stats.add(this);
                out = "Got it. I've added this task:\n" + taskAdded + "\n" + "Now you have "
                        + list.size() + " tasks in the list.";
            }
            storage.saveTask(list);
            statStorage.saveStats(stats);
        return out;
    }

    public String toString() {
        return "Added task: " + taskAdded.toString();
    }
}
