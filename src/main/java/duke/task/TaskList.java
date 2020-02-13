package duke.task;

import duke.Copyable;
import duke.storage.CSV;

import java.util.List;
import java.util.ArrayList;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskList implements Copyable {

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

    public void add(Task t, int idx) {
        this.lst.add(idx, t);
    }

    public Task get(int idx) {
        return this.lst.get(idx);
    }

    public Task remove(int idx) {
        return this.lst.remove(idx);
    }

    public void removeScrapped() {
        this.lst = this.lst.stream().filter(x -> !x.isScrapped()).collect(Collectors.toList());
    }

    public int count() {
        return this.lst.size();
    }

    public List<Task> getTaskList() {
        return this.lst;
    }

    /**
     * search for tasks
     *
     * @param searchStr = strings to search for among task names
     * @return list of tasks which name matches the search strings
     */
    public List<Task> find(String... searchStr) {
        Stream<Task> ans = new ArrayList<>(this.lst).stream();
        for (String str : searchStr) {
            ans = ans.filter(x -> x.getName().contains(str));
        }
        return ans.collect(Collectors.toList());
    }

    public List<Task> filter(Predicate<Task> p) {
        return this.lst.stream().filter(p).collect(Collectors.toList());
    }

    public Stream<Task> stream() {
        return this.lst.parallelStream();
    }

    public static TaskList fromCSVList(List<CSV> lst) {
        return new TaskList(lst.stream().map(Task::parseFromCSV).collect(Collectors.toList()));
    }

    public TaskList getCopy() {
        return new TaskList(this.lst.stream().map(x -> x.getCopy()).collect(Collectors.toList()));
    }
}