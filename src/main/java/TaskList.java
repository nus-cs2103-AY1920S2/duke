import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public String add(Task task) {
        this.list.add(task);
        return "New task has been added:\n    " + task
                + "\nNow you have "+ list.size() + (list.size() == 1 ? " task" : " tasks") + " in the list.";
    }

    public String newTodo(char taskType, boolean isDone, String taskName) {
        Task task = new Todo(taskType, isDone, taskName);
        return add(task);
    }

    public String newEvent(char taskType, boolean isDone, String taskName, String taskTime) {
        Task task = new Event(taskType, isDone, taskName, taskTime);
        return add(task);
    }

    public String newDeadline(char taskType, boolean isDone, String taskName, String taskTime) {
        Task task = new Deadline(taskType, isDone, taskName, taskTime);
        return add(task);
    }

    public String markDone(int taskID) {
        return list.get(taskID - 1).markDone();
    }

    public String deleteTask(int taskID) {
        String output = "Removed this task:\n    " + list.get(taskID - 1);
        list.remove(taskID - 1);
        output = output + "\nNow you have "+ list.size() + (list.size() == 1 ? " task" : " tasks") + " in the list.";
        return output;
    }

    public int getSize() {
        return list.size();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output = output + (i + 1) + ". " + list.get(i) + "\n";
        }
        return output;
    }
}