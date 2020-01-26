package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * A convenient interface that provides a method to generate replies.
 */
public interface TaskCreation {
    default String CreateTaskReply(Task newTask, TaskList tasks) {
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", newTask,
                tasks.size());
    }
}