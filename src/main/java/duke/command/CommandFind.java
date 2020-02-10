package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.interact.UiDesign;
import duke.task.Task;

/**
 * Represents the find command where the tasks that possess the keyword(s) given by the user will be listed.
 */
public class CommandFind implements Command {

    private StringBuilder appendedText;
    private String keyword;

    /**
     * Remembers the keyword(s) that the user input.
     * @param keyword String of the keyword(s).
     */
    public CommandFind(String keyword) {
        this.keyword = keyword;
    }


    /**
     * Appends the Tasks that contains the keyword(s) into appendedText for the output.
     * @param tasks TaskList where the Tasks will be searched from.
     * @param storage Storage to save data.
     * @throws DukeException Exception thrown if saving fails.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        appendedText = new StringBuilder(UiDesign.FIND_TOP_PART.getString());
        for (int i = 1; i <= tasks.getSize(); i++) {
            Task task = tasks.getTask(i - 1);
            if (task.getDetails().contains(keyword)) {
                appendedText.append(i).append(".").append(task).append("\n");
            }
        }
        appendedText.append(UiDesign.BORDER.getString());
        storage.save(tasks);
    }

    @Override
    public String output() {
        return appendedText.toString();
    }
}
