package duke.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * TaskList
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * TaskList class defines the behavior of task
 * management.
 * @author Mario Lorenzo
 */

public class TaskList implements IList<Task> {
    ArrayList<Task> tasks;

    /**
     * Constructs a TaskList instance.
     * @param tasks The ArrayList of tasks.
     */

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Verifies whether the index
     * provided is within the range or not.
     * @param index the index of the task
     * @return the boolean value of whether the index given is within the valid range.
     */

    public boolean isNotInRange(int index) {
        return index <= 0 || index > tasks.size() || tasks.size() <= 0;
    }

    /**
     * Marks the task of a particular index
     * to be done, and inform the client later. This method is
     * being called when a done command is entered by a client.
     * @param index The index of a particular task in the task list.
     */

    public String markDone(int index, Storage storage) {
        Task task = getTask(index);
        task.markAsDone();
        storage.rewriteToFile(tasks);

        return "Nice! I've marked this task as done: \n"
                + String.format("   %s\n", task.toString())
                + String.format("Now you have %d task(s) in the list.", tasks.size());
    }

    /**
     * Gets the task of a given index
     * from the list of tasks. The index value is normalized
     * by subtracting the value with 1 since the value starts
     * from 1.
     * @param index The index of the task in the list.
     * @return The Task instance of an index provided by the client.
     */

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Prints all the tasks stored in the ArrayList
     * of the Duke instance.
     */

    public String listTasks() {
        if (tasks.size() == 0) {
            return "The list is currently empty. Fill me please!";
        }
        String message = "Here's your current tasks:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            message += String.format("%d. %s", i, tasks.get(i - 1));

            if (i != tasks.size()) {
                message += "\n";
            }
        }

        return message;
    }

    /**
     * Adds the task with the corresponding
     * type, and the description provided by the client.
     * @param task The task that wants to be added to the list.
     */

    public String addTask(Task task, Storage storage) {
        tasks.add(task);
        boolean isAppendMode = tasks.size() != 1;
        storage.write(task, isAppendMode);
        return "Got it. I've added this task: \n"
                + String.format("    %s\n", task)
                + String.format("Now you have %d task(s) in the list.", tasks.size());
    }

    /**
     * Deletes the task of a particular index
     * from the list, then remove it from the file.
     * @param index The index of the task in the list to be deleted.
     */

    public String deleteTask(int index, Storage storage) {
        Task task = getTask(index);
        tasks.remove(index - 1);
        storage.rewriteToFile(tasks);
        return "Noted. I've removed this task: \n "
                + String.format("    %s\n", task)
                + String.format("Now you have %d task(s) in the list.", tasks.size());
    }

    /**
     * Finds the task that contains a particular keyword.
     * @param key The keyword.
     * @return The message to be displayed.
     */

    public String findTask(String key) {
        ArrayList<Task> filteredTasks = this.tasks.stream()
                .filter(x -> x.getDescription().contains(key))
                .collect(Collectors.toCollection(ArrayList::new));
        String message = "Here are the matching tasks in your list:";

        if (filteredTasks.size() == 0) {
            return "OOPS! There is no matching task in your list.";
        }

        for (int i = 1; i <= filteredTasks.size(); i++) {
            message += String.format("\n%d. %s", i, filteredTasks.get(i - 1));
        }

        return message;
    }

    /**
     * Lists the reminder of the top 3 approaching deadline and event.
     * @return The output message of the reminder.
     */

    public String listReminder() {
        ArrayList<Deadline> filterDeadline = this.tasks.stream()
                .filter(x -> (x instanceof Deadline) && !x.getStatus())
                .map(x -> (Deadline)x)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Event> filterEvent = this.tasks.stream()
                .filter(x -> (x instanceof Event) && !x.getStatus())
                .map(x -> (Event)x)
                .collect(Collectors.toCollection(ArrayList::new));

        Collections.sort(filterDeadline, Comparator.comparing(Deadline::getDueDate));

        Collections.sort(filterEvent, Comparator.comparing(Event::getEventSchedule));

        String message = "Here is the approaching deadline:";
        if (filterDeadline.size() == 0) {
            message = "You have no approaching deadline.";
        }

        for (int i = 0; i < Math.min(filterDeadline.size(), 3); i++) {
            message += String.format("\n%d. %s", i + 1, filterDeadline.get(i));
        }

        if (filterEvent.size() == 0) {
            message += "\nAnd you don't have any upcoming event :)";
        } else {
            message += "\nAnd here is the approaching event:";
        }

        for (int i = 0; i < Math.min(filterEvent.size(), 3); i++) {
            message += String.format("\n%d. %s", i + 1, filterEvent.get(i));
        }
        return message;
    }

    /**
     * Returns the size of the list.
     * @return The size of the list.
     */

    public int size() {
        return this.tasks.size();
    }
}