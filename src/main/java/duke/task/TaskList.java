package duke.task;
import duke.storage.CSV;

import java.util.List;
import java.util.ArrayList;

import java.util.stream.Collectors;

public class TaskList {

	private List<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    TaskList(List<Task> lst) {
        this.lst = lst;
    }

    public boolean add(Task t) {
        return this.lst.add(t);
    }

    public Task get(int idx) {
        return this.lst.get(idx);
    }

    public Task remove(int idx) {
        return this.lst.remove(idx);
    }

    public int count() {
        return this.lst.size();
    }

    public List<Task> getTaskList() {
        return this.lst;
    }

    public static TaskList fromCSVList(List<CSV> lst) {
        return new TaskList(lst.stream().map(Task::parseFromCSV).collect(Collectors.toList()));
    }
}