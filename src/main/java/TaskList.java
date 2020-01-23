import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public String listTasks() {
        String formattedString = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            formattedString += (i + 1) + ". " + this.taskList.get(i).toString() + "\n    ";
        }
        return formattedString.trim();
    }
}