import java.util.ArrayList;

public class Todo {
    private ArrayList<Task> list;

    public Todo() {
        this.list = new ArrayList<Task>();
    }

    public String add(String taskName) {
        Task task = new Task(taskName);
        this.list.add(task);
        return "New task has been added: " + taskName;
    }

    public String markDone(int taskID) {
        return list.get(taskID - 1).markDone();
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
