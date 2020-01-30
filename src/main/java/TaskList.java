import java.util.ArrayList;

public class TaskList {
  public ArrayList<Task> tasks;

  TaskList() {
    tasks = new ArrayList<>();
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public ArrayList<Task> getTaskList() {
    return tasks;
  }
}
