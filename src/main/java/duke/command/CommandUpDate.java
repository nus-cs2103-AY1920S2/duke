package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.DukeExceptionDateFormat;
import duke.exception.DukeExceptionDescription;
import duke.exception.DukeExceptionIndex;
import duke.interact.UiDesign;
import duke.task.DateTask;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents the Update Date command where the date of a Deadline Task or an Event Task can be changed.
 */
public class CommandUpDate implements Command {

    private StringBuilder appendedText;
    private int indexToUpdate;
    private LocalDate newDate;

    /**
     * Saves the index of the task being update and the new date.
     * @param line String the user had input.
     * @param size Integer size of TaskList.
     * @throws DukeException Exception thrown when user input an incorrect index.
     */
    public CommandUpDate(String line, int size) throws DukeException {
        try {
            String[] splitThroughWhitespace = line.split("\\s", 3);
            if (splitThroughWhitespace.length < 3) {
                throw new DukeExceptionDescription("update date");
            }

            indexToUpdate = Integer.parseInt(splitThroughWhitespace[1]) - 1;
            if (indexToUpdate < 0 || indexToUpdate > size - 1) {
                throw new DukeExceptionIndex("update date");
            }

            newDate = LocalDate.parse(splitThroughWhitespace[2]);
        } catch (DateTimeParseException e) {
            throw new DukeExceptionDateFormat();
        }
    }

    /**
     * Changes the date for the Task and appends the text into appendedText for output.
     * @param tasks TaskList where the Task being changed will be found from.
     * @param storage Storage to save data.
     * @throws DukeException Exception thrown if saving fails.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        appendedText = new StringBuilder(UiDesign.UPDATE_TOP_PART.getString());
        DateTask task = (DateTask) tasks.getTask(indexToUpdate);
        task.changeDate(newDate);
        appendedText.append(tasks.getTask(indexToUpdate))
                .append("\n")
                .append(UiDesign.BORDER.getString());
        storage.save(tasks);
    }

    @Override
    public String output() {
        return appendedText.toString();
    }
}
