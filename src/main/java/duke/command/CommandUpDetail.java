package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.DukeExceptionDescription;
import duke.exception.DukeExceptionIndex;
import duke.interact.UiDesign;
import duke.task.Task;

/**
 * Represents the Update Detail command where the detail of a Task can be changed.
 */
public class CommandUpDetail implements Command {

    private StringBuilder appendedText;
    private int indexToUpdate;
    private String newDetail;

    /**
     * Saves the index of the task being update and the new detail.
     * @param line String the user had input.
     * @param size Integer size of TaskList.
     * @throws DukeException Exception thrown when user input an incorrect index.
     */
    public CommandUpDetail(String line, int size) throws DukeException {
        String[] splitThroughWhitespace = line.split("\\s", 3);
        if (splitThroughWhitespace.length < 3) {
            throw new DukeExceptionDescription("update detail");
        }

        indexToUpdate = Integer.parseInt(splitThroughWhitespace[1]) - 1;
        newDetail = " " + splitThroughWhitespace[2];
        if (indexToUpdate < 0 || indexToUpdate > size - 1) {
            throw new DukeExceptionIndex("update detail");
        }
    }

    /**
     * Changes the details for the Task and appends the text into appendedText for output.
     * @param tasks TaskList where the Task being changed will be found from.
     * @param storage Storage to save data.
     * @throws DukeException Exception thrown if saving fails.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        appendedText = new StringBuilder(UiDesign.UPDATE_TOP_PART.getString());
        Task task = tasks.getTask(indexToUpdate);
        task.changeDetail(newDetail);
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
