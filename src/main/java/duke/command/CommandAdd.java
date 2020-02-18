package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.DukeExceptionDate;
import duke.exception.DukeExceptionDateFormat;
import duke.exception.DukeExceptionDescription;
import duke.interact.UiDesign;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents an command that adds a new Task to the TaskList.
 */
public class CommandAdd implements Command {

    private StringBuilder appendedText;
    Task taskToBeAdded;

    /**
     * Creates the Todo Task and remembers it.
     * @param details String containing the details of the task.
     */
    public CommandAdd(String details) {
        if (details.isBlank()) {
            throw new DukeExceptionDescription("todo");
        }
        taskToBeAdded = new ToDo(details);
    }

    /**
     * Creates the Event or Deadline Task to be added and remembers it.
     * @param line String the user input
     * @param type String of the command
     * @throws DukeException Exception thrown when user input an incorrect date or left the details blank.
     */
    public CommandAdd(String line, String type) throws DukeException {
        String details = line.substring(type.length());
        if (details.isBlank()) {
            throw new DukeExceptionDescription(type);
        }

        String[] msgAndDate = details.split(" /", 2);
        if (msgAndDate.length == 1) {
            throw new DukeExceptionDate(type);
        }
        String tagString = msgAndDate[1].substring(0, 2);
        String dateString = msgAndDate[1].substring(3);
        if ((type.equals("deadline") && !tagString.equals("by"))
                || type.equals("event") && !tagString.equals("at")) {
            throw new DukeExceptionDate(type);
        }

        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeExceptionDateFormat();
        }

        if (type.equals("event")) {
            taskToBeAdded = new Event(msgAndDate[0], date);
        } else if (type.equals("deadline")) {
            taskToBeAdded = new Deadline(msgAndDate[0], date);
        }
    }

    /**
     * Adds the Task to the TaskList and generates the StringBuilder for output.
     * @param tasks TaskList to store the Task in.
     * @param storage Storage to save data.
     * @throws DukeException Exception thrown if saving fails.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        int numTasks = tasks.getSize() + 1;
        appendedText = new StringBuilder(UiDesign.ADD_TOP_PART.getString());
        appendedText.append(taskToBeAdded)
                .append("\n")
                .append("Now you have ").append(numTasks).append(" tasks in the list.\n")
                .append(UiDesign.BORDER.getString());
        tasks.addTask(taskToBeAdded);
        storage.save(tasks);
    }

    @Override
    public String output() {
        return appendedText.toString();
    }
}
