package seedu.duke;

import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * TaskList class that contains the task list. Supports operations to add/delete tasks in the list.
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Instantiate an TaskList object with no task.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Instantiate an TaskList object with tasks decoded from text file.
     *
     * @param lines List of strings read from text file.
     */
    public TaskList(List<String> lines) {
        tasks = lines
                .stream()
                .map(str -> TaskList.decode(str))
                .collect(Collectors.toList());
    }

    private static Task decode(String str) {
        String[] splitInput = str.split(Pattern.quote(Task.SEPERATOR));
        String taskType = splitInput[0];
        boolean isDone = splitInput[1].equals(Task.TRUE_SYMBOL);
        String taskDescription = splitInput[2];
        LocalDate date;
        Task toReturn = null;

        switch (taskType) {
        case Todo.TYPE_SYMBOL:
            toReturn = new Todo(taskDescription);
            break;
        case Deadline.TYPE_SYMBOL:
            date = LocalDate.parse(splitInput[3]);
            toReturn = new Deadline(taskDescription, date);
            break;
        case Event.TYPE_SYMBOL:
            date = LocalDate.parse(splitInput[3]);
            toReturn = new Event(taskDescription, date);
            break;
        default:
            assert false : "Failed to decode. Unknown task type.";
            break;
        }

        if (isDone) {
            toReturn.markAsDone();
        }

        return toReturn;
    }

    List<Task> getTasks() {
        return tasks;
    }

    boolean isEmpty() {
        return tasks.isEmpty();
    }

    int size() {
        return tasks.size();
    }

    /**
     * Returns a Task object at specified index position.
     *
     * @param taskIndex Task object index, starting from 0.
     * @return Task object at specified index position.
     */
    public Task get(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Add a task object to the back of the task list.
     *
     * @param newTask Task object to be added.
     */
    public void add(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Deletes a Task object at specified index position.
     *
     * @param taskIndex Task object index, starting from 0.
     */
    public void remove(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * Returns a TaskList object, which contains the tasks that matches with the given keyword.
     *
     * @param keywords Keywords to match.
     * @return Immutable TaskList object.
     */
    public TaskList find(String[] keywords) {
        TaskList viewToReturn = new TaskList();
        Set<Task> taskSet = new HashSet<>();

        for (Task task : tasks) {
            for (String keyword : keywords) {
                if (task.getTaskDescription().contains(keyword) && !taskSet.contains(task)) {
                    taskSet.add(task);
                    viewToReturn.add(task);
                }
            }
        }

        return viewToReturn;
    }

    /**
     * Sort tasks in the task list by chronological order.
     */
    public void sort() {
        Collections.sort(tasks);
    }
}
