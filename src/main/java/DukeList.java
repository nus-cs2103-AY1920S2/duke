import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> list;

    public DukeList() {
        this.list = new ArrayList<Task>();
    }

    public String add(Task task) {
        this.list.add(task);


        return "New task has been added:\n    " + task
                + "\nNow you have "+ list.size() + (list.size() == 1 ? " task" : " tasks") + " in the list.";
    }


    public String newTodo(char taskType, String taskName) {
        Task task = new Todo(taskName, taskType);
        return add(task);
    }

    public String newEvent(char taskType, String taskName, String taskTime) {
        Task task = new Event(taskName, taskType, taskTime);
        return add(task);
    }

    public String newDeadline(char taskType, String taskName, String taskTime) {
        Task task = new Deadline(taskName, taskType, taskTime);
        return add(task);
    }


    public String markDone(int taskID) {
        return list.get(taskID - 1).markDone();
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