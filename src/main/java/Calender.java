import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Calender {
    private HashMap<LocalDate, ArrayList<Task>> hm;

    public void addDate(Task task) {
        if (hm.containsKey(task.getDate()))
                hm.get(task.getDate()).add(task);
        else {
            ArrayList<Task> list = new ArrayList<>();
            list.add(task);
            hm.put(task.getDate(), list);
        }
    }

    public ArrayList<Task> searchDate(LocalDate date) {
        if (!hm.containsKey(date)) return new ArrayList<>();
        return hm.get(date);
    }

    public void removeTask(Task task) {
        if (hm.containsKey(task.getDate())) {
            ArrayList<Task> list = hm.get(task.getDate());
            list.remove(task);
        } else {
            System.out.println("Task: " + task
                    + " is not found in the calender");
        }
    }

    public Calender() {
        hm = new HashMap<>();
    }
}
