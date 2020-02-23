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

        int slashIdx = description.indexOf("/");
        if (slashIdx == -1) {
            throw new WrongDeadlineFormatException();
        }

        Task task;
        try {
            String taskTitle = description.substring(0, slashIdx).trim();
            String deadline = description.substring(slashIdx + 4).trim();
            task = new Deadline(taskTitle, Parser.parseDate(deadline));
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new WrongDeadlineFormatException();
        }

        StringBuilder output = new StringBuilder();
        if (task !=  null) {
            tasks.addTask(task);
            storage.saveTask(task);

            output.append("You've added this deadline!\n"
                    + task.toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list\n");
        } else {
            output.append("Deadline was not added. Please try again");
        }
        return output.toString();
    }
}
