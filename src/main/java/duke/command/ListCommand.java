package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class ListCommand extends Command {
    public ListCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] inputTokens = this.command.split(" ");
        String dateString = (inputTokens.length > 1) ? inputTokens[1] : "";
        TaskList filteredTasks = filterTasksByDate(tasks, dateString);
        ui.prettyPrintList(filteredTasks);
    }

    /**
     * Get a list of tasks that occurs at/ needs to be fulfilled by a certain date
     *
     * @param  tasks   the list of tasks to be filtered
     * @param  dateString    the date criteria in string format
     */
    public static TaskList filterTasksByDate(TaskList tasks, String dateString) {
        TaskList filteredTasks;

        // If there is a filter
        if (!dateString.equals("")) {
            LocalDate date = LocalDate.parse(dateString);
            filteredTasks = new TaskList();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task instanceof Event && ((Event) task).getDate().equals(date)) {
                    filteredTasks.add(task);
                } else if (task instanceof Deadline && ((Deadline) task).getDate().equals(date)) {
                    filteredTasks.add(task);
                }
            }
        } else {
            filteredTasks = tasks;
        }

        return filteredTasks;
    }
}
