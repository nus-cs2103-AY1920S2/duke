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

    public void addState(TaskList current) {
       if (!this.canAdd()) {
           this.history.removeLast();
       }
       this.history.addFirst(this.copyList(current));
    }

    public TaskList getLastState(TaskList current) {
        if (canUndo()) {
            return this.history.removeFirst();
        } else {
            return current; // return itself, cannot undo
        }
    }

    public Task copyTask(Task task) {
        if (task instanceof ToDo) {
            return new ToDo(task.getTaskName(), task.getPriority());
        } else if (task instanceof Deadline) {
            return new Deadline(task.getTaskName(),((Deadline) task).getDateTime() ,task.getPriority());
        } else {
            return new Event(task.getTaskName(), ((Event)task).getDateTime() ,task.getPriority());
        }
    }
}
