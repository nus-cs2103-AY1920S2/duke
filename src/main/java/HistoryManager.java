import java.util.ArrayList;
import java.util.LinkedList;

public class HistoryManager {
    private final int size = 5; // set the number of undo commands to 5
    private LinkedList<TaskList> history = new LinkedList<>();

    public boolean canAdd() {
        return this.history.size() != this.size;
    }

    public boolean canUndo() {
        return this.history.size() != 0;
    }

    /**
     * Performs a deep copy of the current list.
     * @param list of the current tasks.
     * @return duplicated task list.
     */
    public TaskList copyList(TaskList list) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : list.getTaskList()) {
            Task newTask = this.copyTask(task);
            if (task.getDone()) {
                newTask.setDone();
            }
            tasks.add(newTask);
        }
        return new TaskList(tasks);
    }

    /**
     * Adds the state of the list to the stack for future undo.
     * @param current current state of the list.
     */
    public void addState(TaskList current) {
        if (!this.canAdd()) {
            this.history.removeLast();
        }
        this.history.addFirst(this.copyList(current));
    }

    /**
     * Gets the last state of the task list.
     * @param current current tasklist.
     * @return if no last state, then return the current state.
     */
    public TaskList getLastState(TaskList current) {
        if (canUndo()) {
            return this.history.removeFirst();
        } else {
            return current; // return itself, cannot undo
        }
    }

    /**
     * Performs a deep copy of the task.
     * @param task to be copied.
     * @return deep copy of the task.
     */
    public Task copyTask(Task task) {
        if (task instanceof ToDo) {
            return new ToDo(task.getTaskName(), task.getPriority());
        } else if (task instanceof Deadline) {
            return new Deadline(task.getTaskName(), ((Deadline) task).getDateTime(), task.getPriority());
        } else {
            return new Event(task.getTaskName(), ((Event)task).getDateTime(), task.getPriority());
        }
    }
}
