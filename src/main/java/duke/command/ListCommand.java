package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;
import duke.task.Task;

public class ListCommand extends Command {

    @Override
    public String execute(Ui ui, TaskStorage storage) {
        int counter = 1;
        String text = "";
        for (Task task : storage.getTaskList()) {
            text += "    " + counter + ". " + task + System.lineSeparator();
            counter++;
        }

        String prependedString = getPrependedString(storage);

        return prependedString + "    " + text.trim();
    }

    private String getPrependedString(TaskStorage storage) {
        if (storage.getTaskList().size() != 0) {
            return "    Here are the tasks in your list:" + System.lineSeparator();
        } else {
            return "Your task list is currently empty.";
        }
    }
}