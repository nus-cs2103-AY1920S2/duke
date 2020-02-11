import java.util.ArrayList;
import java.util.Collections;

/**
 * This represents a list of Tasks.
 */
public class TaskList {
    ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList sortDeadlineTask() {
        ArrayList<Task> deadlines = new ArrayList<>();
        for (Task task : this.list) {
            if (task instanceof DeadlineTask) {
                deadlines.add(task);
            }
        }
        Collections.sort(deadlines, (task1, task2) -> task1.compareTo(task2));
        return new TaskList(deadlines);
    }

    public TaskList sortEventTask() {
        ArrayList<Task> events = new ArrayList<>();
        for (Task task : this.list) {
            if (task instanceof EventTask) {
                events.add(task);
            }
        }
        Collections.sort(events, (task1, task2) -> task1.compareTo(task2));
        return new TaskList(events);
    }

    public TaskList sortTodoTask() {
        ArrayList<Task> todos = new ArrayList<>();
        for (Task task : this.list) {
            if (task instanceof ToDoTask) {
                todos.add(task);
            }
        }
        Collections.sort(todos, (task1, task2) -> task1.compareTo(task2));
        return new TaskList(todos);
    }

    @Override
    public String toString() {
        String output = "";
        int index = 1;
        for (Task t : list) {
            output += (index + ". " + t.toString() + "\n");
            index++;
        }
        return output;
    }
}