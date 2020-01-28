package duke.data;

import duke.tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a pseudo-calender using a HashMap to map dates to
 * an ArrayList of tasks occurring on the specific date.
 */
public class Calender {
    /** HashMap representing the calender. */
    private HashMap<LocalDate, ArrayList<Task>> hm;

    /**
     * @param task task to be added into the calender.
     */
    public void addDate(Task task) {
        if (hm.containsKey(task.getDate()))
                hm.get(task.getDate()).add(task);
        else {
            ArrayList<Task> list = new ArrayList<>();
            list.add(task);
            hm.put(task.getDate(), list);
        }
    }

    /**
     * Searches for a specific date in the calender and prints out all tasks due on that date.
     * @param date date to be searched for.
     */
    public void searchDate(LocalDate date) {
        if (hm.containsKey(date)) {
            ArrayList<Task> tasks = hm.get(date);
            for (Task task : tasks) {
                System.out.println("  " + task);
            }
        }
    }

    /**
     * @param task task to be removed.
     * @param date date of task.
     */
    public void removeTask(Task task, LocalDate date) {
        if (hm.containsKey(date)) {
            ArrayList<Task> list = hm.get(date);
            list.remove(task);
        } else {
            System.out.println("Task: " + task
                    + " is not found in the calender");
        }
    }

    /**
     * Constructs an empty calender.
     */
    public Calender() {
        hm = new HashMap<>();
    }
}
