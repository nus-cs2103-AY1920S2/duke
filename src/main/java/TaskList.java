import java.util.List;
import java.util.ArrayList;

import java.util.stream.Collectors;

class TaskList {

	private List<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    TaskList(List<Task> lst) {
        this.lst = lst;
    }

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

    public static TaskList fromCSVList(List<CSV> lst) {
        return new TaskList(lst.stream().map(Task::parseFromCSV).collect(Collectors.toList()));
    }
}