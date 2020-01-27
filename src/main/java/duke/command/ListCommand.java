package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.getTasks().get(i));
        }
    }
}
