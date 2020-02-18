package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.DukeExceptionIndex;
import duke.interact.UiDesign;

/**
 * Represents the done command where the task will be marked done.
 */
public class CommandDone implements Command {

    private StringBuilder appendedText;
    private int index;

    /**
     * Saves the index of the Task that will be marked done.
     * @param line String the user had input.
     * @throws DukeException Exception thrown when user input an incorrect index.
     */
    public CommandDone(String line, int size) throws DukeException {
        String[] splitThroughWhitespace = line.split("\\s", 2);
        if (splitThroughWhitespace.length == 1) {
            throw new DukeExceptionIndex("done");
        }

        index = Integer.parseInt(splitThroughWhitespace[1]) - 1;

        if (index > size - 1) {
            throw new DukeExceptionIndex("done");
        }
    }


    /**
     * Marks the Task at index as done and generates the StringBuilder for output.
     * @param tasks TaskList where the Task that will be marked done is from.
     * @param storage Storage to save data.
     * @throws DukeException Exception thrown if saving fails.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.markDone(index);
        appendedText = new StringBuilder(UiDesign.DONE_TOP_PART.getString());
        appendedText.append(tasks.getTask(index))
                .append("\n")
                .append(UiDesign.BORDER.getString());
        storage.save(tasks);
    }

    @Override
    public String output() {
        return appendedText.toString();
    }
}
