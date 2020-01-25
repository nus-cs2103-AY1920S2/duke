package duke.commands;

import java.util.List;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.exceptions.DukeException;

class ListAll implements Command {
    public void execute(String arg, List<Task> tasks, Ui ui) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are currently no tasks.");
        }
        int counter = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (Task task : tasks) {
            sb.append(counter);
            sb.append(".");
            sb.append(task);
            sb.append("\n");
            counter += 1;
        }
        ui.showReply(sb.toString());
    }
}