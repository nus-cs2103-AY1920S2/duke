package duke.data;

import duke.tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Calender {
    private HashMap<LocalDate, ArrayList<Task>> mapOfDates;

    public void addDate(Task task) {
        if (mapOfDates.containsKey(task.getDate()))
                mapOfDates.get(task.getDate()).add(task);
        else {
            ArrayList<Task> tasks = new ArrayList<>();
            tasks.add(task);
            mapOfDates.put(task.getDate(), tasks);
        }
    }

    public void searchDate(LocalDate date) {
        if (mapOfDates.containsKey(date)) {
            ArrayList<Task> tasks = mapOfDates.get(date);
            for (Task task : tasks) {
                System.out.println("  " + task);
            }
        }
    }

    public void removeTask(Task task, LocalDate date) {
        if (mapOfDates.containsKey(date)) {
            ArrayList<Task> tasks = mapOfDates.get(date);
            tasks.remove(task);
        } else {
            System.out.println("Task: " + task
                    + " is not found in the calender");
        }
    }

    public Calender() {
        mapOfDates = new HashMap<>();
    }
}
