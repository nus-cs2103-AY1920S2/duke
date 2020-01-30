package duke;

import java.util.ArrayList;

/**
 * Class representing task list
 */
public class TaskList {
  public ArrayList<Task> tasks;

  TaskList() {
    tasks = new ArrayList<>();
  }

  /**
   * Adds the task to list
   * @param task task to add
   */
  public void addTask(Task task) {
    tasks.add(task);
  }

  /**
   * Get method to retrieve the list
   * @return task list
   */
  public ArrayList<Task> getTaskList() {
    return tasks;
  }
}
