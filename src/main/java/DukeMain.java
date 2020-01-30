import java.util.*;

public class DukeMain {
    private List<Task> lst = new ArrayList<>();

    boolean add(Task t) {
        return this.lst.add(t);
    }

    List<Task> getTaskList() {
        return this.lst;
    }
}
