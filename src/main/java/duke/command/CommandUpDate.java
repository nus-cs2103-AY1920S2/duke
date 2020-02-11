package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.interact.UiDesign;
import duke.task.DateTask;
import duke.task.Task;

import java.time.LocalDate;

public class CommandUpDate implements Command {

    private StringBuilder appendedText;
    private int indexToUpdate;
    private LocalDate newDate;

    public CommandUpDate(int index, LocalDate date) {
        indexToUpdate = index;
        newDate = date;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
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
