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

    public Calender() {
        hm = new HashMap<>();
    }
}
