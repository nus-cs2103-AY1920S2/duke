package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.time.format.DateTimeParseException;

public class AddEventCommand implements Command {

    /**
     * Adds an Event task to TaskList
     * @param description Description about the Event task
     * @param tasks List of current tasks
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong
     */
    public static String execute(String description, TaskList tasks, Storage storage) {

        int slashIdx = description.indexOf("/");
        if (slashIdx == -1) {
            // throw exception
            System.out.println("Wrong event format. Please try again.");
        }
        String taskTitle = description.substring(0, slashIdx).trim();
        String dateTime = description.substring(slashIdx + 4).trim();

        Task task = null;
        try {
            task = new Event(taskTitle, Event.parseDateTime(dateTime));
        } catch (DateTimeParseException e) {
            // throw duke
            System.out.println("Please enter deadline in the following format: YYYY-MM-DD HHMM");
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
