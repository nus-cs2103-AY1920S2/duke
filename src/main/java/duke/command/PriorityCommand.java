package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;
import duke.task.Priority;
import duke.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PriorityCommand extends Command {

    private Priority priority;

    public PriorityCommand(String priority) {
        switch (priority) {
        case "HIGH":
            this.priority = Priority.HIGH;
            break;
        case "MEDIUM":
            this.priority = Priority.MEDIUM;
            break;
        case "LOW":
            this.priority = Priority.LOW;
            break;
        }
    }

    @Override
    public String execute(Ui ui, TaskStorage storage) {
        int counter = 1;
        String text = "";

        ArrayList<Task> storageTaskList = storage.getTaskList();
        ArrayList<Task> prioritisedTaskList = (ArrayList<Task>) storageTaskList
                .stream()
                .filter(task -> task.getPriority().equals(this.priority))
                .collect(Collectors.toList());

        for (Task task : prioritisedTaskList) {
            text += "    " + counter + ". " + task + System.lineSeparator();
            counter++;
        }

        String prependedString = getPrependedString(storage);

        return prependedString + "    " + text.trim();
    }

    private String getPrependedString(TaskStorage storage) {
        if (storage.getTaskList().size() != 0) {
            return "    Here are the " + this.priority + " tasks in your list:" + System.lineSeparator();
        } else {
            return "You do not have any tasks with priority " + this.priority;
        }
    }

    public Priority getPriority() {
        return this.priority;
    }
}
