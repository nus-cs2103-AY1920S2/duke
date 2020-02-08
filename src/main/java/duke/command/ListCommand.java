package duke.command;

import duke.exception.DukeException;
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

    /**
     * List all the tasks in the task list that was saved on the disk, and display to user.
     *
     * @param tasks the task list
     * @param storage the storage object to save the list
     * @param ui the ui object to interact with user
     * @return message to be displayed
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] inputTokens = this.command.split(" ");
        assert inputTokens[0] == "list";
        String dateString = (inputTokens.length > 1) ? inputTokens[1] : "";
        TaskList filteredTasks;
        try {
            filteredTasks = filterTasksByDate(tasks, dateString);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return ui.prettyPrintList(filteredTasks);
    }

    /**
     * Get a list of tasks that occurs at/ needs to be fulfilled by a certain date.
     *
     * @param tasks the list of tasks to be filtered
     * @param dateString the date criteria in string format
     * @return the filtered tasklist
     */
    public static TaskList filterTasksByDate(TaskList tasks, String dateString) throws DukeException  {
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