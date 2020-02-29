package duke.util;

import duke.task.Deadline;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the list of tasks. Supports operations such as add task, delete task, list tasks and so on.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialize a tasklist from the given list of tasks.
     * @param tasks The list of existing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Adds a task to the tasklist and updates the data file.
     * @param task The task to be added.
     * @param storage The storage used to write to the data file.
     * @return The message that is later displayed in the user interface.
     */
    public String addTask(Task task, Storage storage) {
        tasks.add(task);
        String addMessage = "Got it. I've added this task:\n" + task
                + String.format("\nnow you have %d tasks in the list.\n", tasks.size());
        storage.updateTaskData(this);
        return addMessage;
    }

    /**
     * Deletes a task and updates the data file.
     * @param index The index of the task to be deleted.
     * @param storage The storage used to write to the data file.
     * @return The message that is later displayed in the user interface.
     */
    public String deleteTask(int index, Storage storage) {
        Task task = getTask(index);
        tasks.remove(task);
        String deleteMessage = "Noted. I've removed this task:\n" + task
                + String.format("\nnow you have %d tasks in the list.\n", tasks.size());
        storage.updateTaskData(this);
        return deleteMessage;
    }

    /**
     * Lists out all the exiting tasks.
     * @return The message that is later displayed in the user interface.
     */
    public String listTask() {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("There is currently no pending task. "
                    + "Please add some tasks by using the \"todo\", \"event\" or \"deadline\" command\n");
        } else {
            sb.append("Here are all the tasks in your list:\n").append(tasksToString(tasks));
        }
        return sb.toString();
    }

    /**
     * Marks a task in the tasklist as done and updates the data file.
     * @param index The index of the task to be marked as done.
     * @param storage The storage used to write to the data file.
     * @return The message that is later displayed in the user interface.
     */
    public String doneTask(int index, Storage storage) {
        Task task = getTask(index);
        task.markAsDone();
        String doneMessage = "Nice! Congratulations for completing this task:\n" + task + "\n";
        storage.updateTaskData(this);
        return doneMessage;
    }

    /**
     * Finds the tasks that contain the give keyword.
     * @param keyword The keyword that needs to be searched.
     * @return The matching tasks.
     */
    public String findTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        String findMessage = "";
        if (matchingTasks.size() == 0) {
            findMessage = "Sorry, I can't find any task that matches the keyword :(\nMaybe you want to try a different"
                    + " keyword? :D\n";
        } else {
            findMessage = "Here are the matching tasks in your list:\n" + tasksToString(matchingTasks);
        }
        return findMessage;
    }

    public String sortDeadline() {
        ArrayList<Deadline> deadlines = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                deadlines.add((Deadline) task);
            }
        }
        Collections.sort(deadlines);
        return "Here are your deadlines in chronologial order:\n" + tasksToString(deadlines);
    }

    private String tasksToString(ArrayList<? extends Task> tasks) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Task task : tasks) {
            sb.append(String.format("%d. ", ++index)).append(task.toString()).append("\n");
        }
        return sb.toString();
    }
}
