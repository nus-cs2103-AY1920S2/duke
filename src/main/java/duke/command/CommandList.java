package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.interact.UiDesign;

/**
 * Represents the list command where all current tasks will be listed.
 */
public class CommandList implements Command {

    private StringBuilder appendedText;

    /**
     * Appends the Tasks into the StringBuilder for the output.
     * @param tasks TaskList to be listed.
     * @param storage Storage to save data.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        appendedText = new StringBuilder(UiDesign.LIST_TOP_PART.getString());
        for (int i = 1; i <= tasks.getSize(); i++) {
            appendedText.append(i).append(".")
                    .append(tasks.getTask(i - 1)).append("\n");
        }
        appendedText.append(UiDesign.BORDER.getString());
    }

    @Override
    public String output() {
        return appendedText.toString();
    }
}
