import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) throws DukeException {
        if (list.isEmpty()) throw new DukeException("Wrong input");
        else this.taskList = list;
    }

    public Task addTask(Task task) {
        this.taskList.add(task);
        return task;
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public Task removeTask(int index) {
        return this.taskList.remove(index);
    }
}
