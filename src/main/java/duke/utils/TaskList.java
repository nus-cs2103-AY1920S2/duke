package duke.utils;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.Todos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Task List class to represent a list of task and handle
 * task list operations
 */
public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    /**
     * Task List constructor
     * @param tasks list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Task list constructor with empty list
     */
    public TaskList() { }

    public List<Task> getList() {
        return tasks;
    }

    /**
     * Get size of task list
     * @return size of task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Mark task at certain index as done
     * @param taskId to be marked as done
     */
    public void markTaskAsDone(int taskId) {
        tasks.get(taskId).markAsDone();
    }

    /**
     * remove task at certain index
     * @param taskId to be removed
     */
    public void removeTask(int taskId) {
        tasks.remove(taskId);
    }

    /**
     * Find tasks from task list with a given keyword
     * @param word
     * @return list of tasks with given keyword
     */
    public List<Task> findTasksByKeyword(String word) {
        List<Task> searchedTask = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String[] tokens = tasks.get(i).getTaskName().split(" ");
            if(Arrays.asList(tokens).contains(word)) {
                searchedTask.add(tasks.get(i));
            }
        }
        return searchedTask;
    }

    /**
     * Add item to task list
     * @param item name
     * @param type of item
     * @return whether addition is successful or not
     * @throws ParseException
     */
    public boolean addToList(String item, String type) throws ParseException {

        Task newTask;
        String[] tokens;
        String time;

        switch (type) {
            case "todo":
                newTask = new Todos(item);
                break;
            case "deadline":
                tokens = item.split("/", 2);
                if (tokens.length < 2) {
                    return false;
                }
                if (!tokens[1].substring(0, 2).equals("by")) {
                    return false;
                }
                time = tokens[1].substring(2).trim();
                if (!Parser.checkDateFormat(time)) {
                    return false;
                }
                newTask = new Deadlines(tokens[0].trim(), Parser.stringToDate(time));
                break;
            case "event":
                tokens = item.split("/", 2);
                if (tokens.length < 2) {
                    return false;
                }
                if (!tokens[1].substring(0, 2).equals("at")) {
                    return false;
                }
                time = tokens[1].substring(2).trim();
                if (!Parser.checkDateFormat(time)) {
                    return false;
                }
                newTask = new Events(tokens[0].trim(), Parser.stringToDate(time));
                break;
            default:
                newTask = null;
        }
        tasks.add(newTask);
        return true;
    }

    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "No scheduled task yet";
        }
        String list = "1." + tasks.get(0);
        for (int i = 2; i < tasks.size() + 1; i++) {
            list = list + "\n" + i + "." + tasks.get(i - 1);
        }
        return list;
    }
}
