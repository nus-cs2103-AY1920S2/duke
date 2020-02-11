package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.text.ParseException;

public class AddDeadlineCommand implements Command {

    /**
     * Adds a Deadline task to TaskList
     * @param description Description about the Deadline task
     * @param tasks List of current tasks
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong
     */
    public static String execute(String description, TaskList tasks, Storage storage) {

        int slashIdx = description.indexOf("/");
        if (slashIdx == -1) {
            // throw exception
            System.out.println("Wrong deadline format. Please try again.");
        }

        String taskTitle = description.substring(0, slashIdx).trim();
        String deadline = description.substring(slashIdx + 4).trim();

        Task task = null;
        try {
            task = new Deadline(taskTitle, Deadline.parseDate(deadline));
        } catch (ParseException e) {
            // throw duke
            System.out.println("Please enter deadline in the following format: YYYY-MM-DD");
        }

        tasks.addTask(task);
        storage.saveTask(task);

        StringBuilder output = new StringBuilder();
        output.append("This task has been added successfully:\n"
                + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list\n");

        return output.toString();
    }
}
