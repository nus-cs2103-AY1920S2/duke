package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.WrongDeadlineFormatException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.time.format.DateTimeParseException;

public class AddDeadlineCommand implements Command {

    /**
     * Adds a Deadline task to TaskList
     * @param description Description about the Deadline task
     * @param tasks List of current tasks
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong
     */
    public static String execute(String description, TaskList tasks, Storage storage) throws DukeException {
        String[] input = description.split("/by", 2);

        Task task;
        try {
            String taskTitle = input[0].trim();
            String deadline = input[1].trim();
            task = new Deadline(taskTitle, Parser.parseDate(deadline));
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new WrongDeadlineFormatException();
        }

        StringBuilder output = new StringBuilder();
        if (task !=  null) {
            tasks.addTask(storage, task);

            output.append("You've added this deadline!\n"
                    + task.toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list\n");
        } else {
            output.append("Deadline was not added. Please try again");
        }
        return output.toString();
    }
}
