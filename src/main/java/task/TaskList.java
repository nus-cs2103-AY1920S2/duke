package task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int idx) {
        return this.tasks.get(idx);
    }

    public void addNewTodo(String task) {
        this.tasks.add(new Todo(task));
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addNewDeadline(String task, LocalDateTime time) {
        tasks.add(new Deadline(task, time));
    }

    public void addNewEvent(String task, LocalDateTime time) {
        tasks.add(new Event(task, time));
    }

    public int getTaskListSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getCurrentTasks() {
        return this.tasks;
    }

    /**
     * Removes the indexed tasks from the task list.
     *
     * @param taskIndexes indexes of tasks to be removed.
     */
    public void removeTasks(int[] taskIndexes) {
        List<Task> tasksToDelete = IntStream.of(taskIndexes)
                                            .mapToObj(index -> this.tasks.get(index))
                                            .collect(Collectors.toList());
        this.tasks.removeAll(tasksToDelete);
    }

    /**
     * Gets a list of task actions for all tasks.
     *
     * @return list of task actions.
     */
    public List<String> getAllTasksAction() {
        List<String> taskActions = this.tasks.stream()
                                             .map(task -> task.getTaskAction())
                                             .collect(Collectors.toList());
        return taskActions;
    }

    /**
     * Checks whether a task has already existed in the task list.
     *
     * @param newTask task to check whether it has existed.
     * @return boolean.
     */
    public boolean checkDuplicate(Task newTask) {
        List<String> taskActions = this.tasks.stream()
                                             .map(task -> task.getTaskAction())
                                             .collect(Collectors.toList());

        return taskActions.contains(newTask.getTaskAction());
    }
}
