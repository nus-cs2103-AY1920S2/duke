package duke.commands;

import java.util.List;
import duke.tasks.Task;

public interface TaskCreation {
    default String CreateTaskReply(Task newTask, List<Task> tasks) {
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", newTask,
                tasks.size());
    }
}