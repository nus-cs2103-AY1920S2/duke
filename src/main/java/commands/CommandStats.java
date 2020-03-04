package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TodoTask;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommandStats implements Command {
    @Override
    public String execute(DukeProcessor processor, String args) throws DukeException {
        int notDoneCount = 0;
        int doneCount = 0;

        int todoTasksCount = 0;
        int deadlineTasksCount = 0;
        int eventTasksCount = 0;

        int tasksPastEndDate = 0;
        List<Task> overdueTasksList = new ArrayList<Task>();

        for (Task task : processor.getTaskList().getTasks()) {
            if (task.isDone()) {
                doneCount++;
            } else {
                notDoneCount++;
            }

            if (task instanceof TodoTask) {
                todoTasksCount++;
            } else if (task instanceof DeadlineTask) {
                deadlineTasksCount++;
                if (((DeadlineTask) task).getParsedDeadline().isBefore(LocalDateTime.now()) && !task.isDone()) {
                    overdueTasksList.add(task);
                    tasksPastEndDate++;
                }
            } else {
                if (((EventTask) task).getParsedEndTime().isBefore(LocalDateTime.now()) && !task.isDone()) {
                    overdueTasksList.add(task);
                    tasksPastEndDate++;
                }
                eventTasksCount++;
            }
        }

        String overdueTasksString = "";
        for (Task task : overdueTasksList) {
            overdueTasksString += task.toString() + "\n";
        }

        return String.format("Current task statistics as at %s:\n"
                + "----------\n"
                + "Uncompleted tasks: %d\n"
                + "Completed tasks: %d\n"
                + "----------\n"
                + "Todos: %d\n"
                + "Deadlines: %d\n"
                + "Events: %d\n"
                + "----------\n"
                + "The following %d tasks are overdue:\n%s\n"
                + "Please make sure you complete them soon!\n\n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")).toString(),
                doneCount, notDoneCount, todoTasksCount, deadlineTasksCount, eventTasksCount,
                tasksPastEndDate, overdueTasksString);
    }
}
