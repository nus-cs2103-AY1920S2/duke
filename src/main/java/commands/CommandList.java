package commands;

import processor.DukeProcessor;
import tasks.Task;

import java.util.List;

/**
 * Command that prints out the list of tasks as entered by the user, in the order that they were entered.
 */
public class CommandList implements Command {

    /**
     * Prints the list of tasks.
     *
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     */
    public String execute(DukeProcessor processor, String args) {
        List<Task> taskList = processor.getTaskList().getTasks();
        if (taskList.size() == 0) {
            return "Looks like you don't have any tasks entered! Try entering one with the "
                    + "commands 'todo', 'deadline' or 'event'!";
        }
        String taskListString = "";
        for (int i = 0; i < taskList.size(); i++) {
            taskListString += String.format("%d. %s", i + 1, taskList.get(i)) + "\n";
        }

        String output = String.format("%s\n%s\n", "Here are the " + taskList.size() + " tasks I've noted down "
                        + "for you:",
                taskListString);
        return output;
    }
}
