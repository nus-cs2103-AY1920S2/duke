package jiachen.duke;

import java.util.ArrayList;

/**
 * The type Task list. Wrapper and abstraction for the ArrayList of Task objects
 */
public class TaskList {
  private ArrayList<Task> tasks;

  /** Instantiates a new Task list. */
  public TaskList() {
    tasks = new ArrayList<>();
  }

  /**
   * Instantiates a new Task list.
   *
   * @param tasks the tasks
   */
  public TaskList(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  /**
   * Wrapper around the add function
   *
   * @param task the task
   */
  public void add(Task task) {
    this.tasks.add(task);
  }

  /**
   * Returns undelying ArrayList
   *
   * @return the list
   */
  public ArrayList<Task> getList() {
    return this.tasks;
  }

  /**
   * get task at index
   *
   * @param index the index
   * @return the task
   */
  public Task get(int index) {
    return tasks.get(index);
  }

  /**
   * get size of tasklist
   *
   * @return the int
   */
  public int size() {
    return tasks.size();
  }

  /**
   * Remove task from tasklist
   *
   * @param index the index
   * @return the task
   */
  public Task remove(int index) {
    return tasks.remove(index);
  }
}
