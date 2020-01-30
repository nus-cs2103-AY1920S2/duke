import java.util.*;

public class DukeMain {
    private List<Task> lst = new ArrayList<>();

    boolean add(Task t) {
        return this.lst.add(t);
    }

    Task get(int idx) {
        return this.lst.get(idx);
    }

    Task remove(int idx) {
        return this.lst.remove(idx);
    }

    int count() {
        return this.lst.size();
    }

    List<Task> getTaskList() {
        return this.lst;
    }
}
