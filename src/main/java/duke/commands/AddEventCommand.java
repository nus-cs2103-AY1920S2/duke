package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.WrongEventFormatException;
import duke.parser.Parser;
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
    public static String execute(String description, TaskList tasks, Storage storage) throws DukeException {
        String[] input = description.split("/at", 2);

        Task task;
        try {
            String taskTitle = input[0].trim();
            String[] dateTime = input[1].trim().split(" ", 2);

            String date = dateTime[0];
            String time = dateTime[1];
            task = new Event(taskTitle, Parser.parseDate(date), Parser.parseTime(time));
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new WrongEventFormatException();
        }

        StringBuilder output = new StringBuilder();
        if (task != null) {
            tasks.addTask(storage, task);

            output.append("You've added this event!\n"
                    + task.toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list\n");
        } else {
            output.append("Event was not added. Please try again");
        }
        return output.toString();
    }
}
