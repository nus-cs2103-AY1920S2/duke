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

    public void searchDate(LocalDate date) {
        if (hm.containsKey(date)) {
            ArrayList<Task> tasks = hm.get(date);
            for (Task task : tasks) {
                System.out.println("  " + task);
            }
        }
    }

    public Calender() {
        hm = new HashMap<>();
    }
}
