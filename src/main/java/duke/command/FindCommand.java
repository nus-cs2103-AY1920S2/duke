package duke.command;

import duke.Storage;
import duke.ui.Ui;
import duke.common.Message;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {

    private String search;

    public FindCommand(String search) {
        this.search = search;
    }

    /**
     * Executes the command and display tasks that matches the search
     * string.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = Message.FIND_MESSAGE + "\n";
        output += Message.DIVIDER + "\n";

        int taskCount = 0;

        for (int i = 1; i <= tasks.getLength(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(search)) {
                output += i + "." + task + "\n";
                taskCount++;
            }
        }

        output += Message.DIVIDER + "\n";
        output += Message.showNumberOfTasksFound(taskCount);
        ui.showMessage(output);
    }
}