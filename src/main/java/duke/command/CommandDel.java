package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.DukeExceptionIndex;
import duke.interact.UiDesign;

public class CommandDel implements Command {

    private StringBuilder appendedText;
    private int index;

    /**
     * Saves the index in the TaskList to be deleted in execute.
     * @param line String the user had input.
     * @param size Integer size of TaskList.
     * @throws DukeException Exception thrown when user input an incorrect index.
     */
    public CommandDel(String line, int size) throws DukeException {
        String[] splitThroughWhitespace = line.split("\\s", 2);
        if (splitThroughWhitespace.length == 1) {
            throw new DukeExceptionIndex("delete");
        }

        index = Integer.parseInt(splitThroughWhitespace[1]) - 1;
        if (index > size - 1) {
            throw new DukeExceptionIndex("delete");
        }
        assert index >= 0 : "Index should be greater than 0.";
    }

    /**
     * Deletes the Task from the TaskList and generates the StringBuilder for output.
     * @param tasks TaskList where Task will be deleted from.
     * @param storage Storage to save TaskList.
     * @throws DukeException Exception thrown if TaskList failed to save.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        int numTasks = tasks.getSize() - 1;
        appendedText = new StringBuilder(UiDesign.REMOVE_TOP_PART.getString());
        appendedText.append(tasks.getTask(index))
                .append("\n")
                .append("Now you have ").append(numTasks).append(" tasks in the list.\n")
                .append(UiDesign.BORDER.getString());
        tasks.remTask(index);
        storage.save(tasks);
    }

    @Override
    public String output() {
        return appendedText.toString();
    }
}
