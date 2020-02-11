package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.interact.UiDesign;
import duke.task.Task;

public class CommandUpDetail implements Command {

    private StringBuilder appendedText;
    private int indexToUpdate;
    private String newDetail;

    public CommandUpDetail(int index, String detail) {
        indexToUpdate = index;
        newDetail = detail;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
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
