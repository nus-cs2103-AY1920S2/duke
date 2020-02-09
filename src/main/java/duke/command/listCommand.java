package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;
import duke.task.Task;

public class listCommand extends Command {

    @Override
    public String execute(Ui ui, TaskStorage storage) {
        int counter = 1;
        String text = "";

        for (Task task : storage.getTaskList()) {
            text += "    " + counter + ". " + task + System.lineSeparator();
            counter++;
        }

        return text.trim();
    }
}