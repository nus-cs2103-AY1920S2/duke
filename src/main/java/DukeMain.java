import java.util.*;

public class DukeMain {
    private List<Task> lst = new ArrayList<>();

    boolean add(Task t) {
        return this.lst.add(t);
    }

    Task get(int idx) {
        return this.lst.get(idx);
    } 

    List<Task> getTaskList() {
        return this.lst;
    }
}
