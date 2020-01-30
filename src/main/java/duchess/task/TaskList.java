package duchess.task;

import duchess.exception.DuchessException;
import duchess.util.Pair;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public Task completeTask(int index) throws DuchessException {
        Task taskToComplete = this.getTask(index);
        if (taskToComplete.isCompleted()) {
            throw new DuchessException("You have already completed this task!");
        }
        taskToComplete.toggleIsCompleted();
        return taskToComplete;
    }

    public ArrayList<Task> getTaskArray() {
        return this.tasks;
    }

    public ArrayList<Pair<Task, Integer>> find(String searchWords) {
        return IntStream.range(0, this.tasks.size())
                .mapToObj(i -> new Pair<Task, Integer>(this.tasks.get(i), i))
                .filter(p -> p.getFirst().getDescription().toLowerCase().contains(searchWords.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
