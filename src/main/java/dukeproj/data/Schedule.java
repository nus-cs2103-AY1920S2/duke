package dukeproj.data;

import dukeproj.enums.TType;
import dukeproj.tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a scheduler using a HashMap to map dates to
 * an ArrayList of tasks occurring on the specific date.
 */
public class Schedule {
    /** HashMap representing the schedule. */
    private HashMap<LocalDate, ArrayList<Task>> mapOfDates;

    /**
     * Adds a task into the schedule.
     *
     * @param task task to be added into the schedule.
     */
    public void addDate(Task task) {
        if (mapOfDates.containsKey(task.getDate())) {
            mapOfDates.get(task.getDate()).add(task);
        } else {
            ArrayList<Task> tasks = new ArrayList<>();
            tasks.add(task);
            mapOfDates.put(task.getDate(), tasks);
        }
    }

    /**
     * Searches for a specific date in the schedule and returns all tasks due on that date.
     *
     * @param date date to be searched for.
     * @return a string of the tasks found in the specific date.
     */
    public String searchDate(LocalDate date) {
        StringBuilder sb = new StringBuilder();
        if (mapOfDates.containsKey(date)) {
            ArrayList<Task> tasks = mapOfDates.get(date);
            for (Task task : tasks) {
                sb.append("  ").append(task).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Removes a task from the schedule.
     *
     * @param task task to be removed.
     * @param date date of task.
     */
    public void removeTask(Task task, LocalDate date) {
        if (mapOfDates.containsKey(date)) {
            ArrayList<Task> tasks = mapOfDates.get(date);
            tasks.remove(task);
        } else if (task.getType().equals(TType.TODO)) {
            // do nothing as type is todo
        } else {
            System.out.println("Task: " + task
                    + " is not found in the schedule");
        }
    }

    /**
     * Constructs an empty schedule.
     */
    public Schedule() {
        mapOfDates = new HashMap<>();
    }
}
