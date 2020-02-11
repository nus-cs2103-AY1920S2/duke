package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A command object for listing the tasks in the list.
 */
public class ScheduleCommand extends Command {
    private final LocalDate date;

    /**
     * Constructs a command object to display tasks
     * at a certain date.
     * @param date The date of interest.
     */
    public ScheduleCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the command to lists out the tasks in the list.
     * @param tasks List of tasks of the Duke object.
     * @param ui UI object of the Duke object.
     * @param storage Storage object of the Duke object.
     * @return String as the response of the execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTaskWithDate = ScheduleCommand.filterTaskAtDate(tasks, this.date);
        ArrayList<Task> taskAtDate = new ArrayList<>();
        for (Task t:filteredTaskWithDate) {
            if (t instanceof Deadline) {
                Deadline curr = (Deadline) t;
                if (curr.getDate().toLocalDate().equals(this.date)) {
                    taskAtDate.add(curr);
                }
            } else if (t instanceof Event) {
                Event curr = (Event) t;
                if (curr.getDate().toLocalDate().equals(this.date)) {
                    taskAtDate.add(curr);
                }
            }
        }
        return Ui.showSchedule(TaskList.of(taskAtDate), this.date);
    }

    /**
     * Filters the list of task to the one that contains a certain string.
     * @return A TaskList that has been filtered
     **/
    public static ArrayList<Task> filterTaskAtDate (TaskList tasks, LocalDate date) {
        ArrayList<Task> al = new ArrayList<>(
                tasks.getTasks().stream()
                        .filter(x -> x.hasDate()).collect(Collectors.toList()));
        return al;
    }
}