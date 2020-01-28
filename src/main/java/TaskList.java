import java.util.ArrayList;

/** TaskList class holds an ArrayList of all the tasks. */
public class TaskList {

  private static ArrayList<Task> taskList = new ArrayList<>();
  private ArrayList<String> stringArr;

  /**
   * Constructor. Initialises the task list.
   *
   * @param stringArr stringArr is taken in as a parameter for the method parseTaskList.
   */
  TaskList(ArrayList<String> stringArr) {
    this.stringArr = stringArr;
    if (!stringArr.isEmpty()) {
      parseTaskList(stringArr);
    }
  }

  /**
   * This method converts each line in the text file to a Task object.
   *
   * @param stringArr Takes in an array of Strings in a certain format which is then converted to
   *     individual Task objects.
   */
  private void parseTaskList(ArrayList<String> stringArr) {
    for (String st : stringArr) {
      String[] split = st.split(",");
      for (int i = 0; i < split.length; i++) {
        String currString = split[i];
        split[i] = currString.trim();
      }
      switch (split[0]) {
        case "E":
          taskList.add(new Event(split[2], split[3], split[1]));
          break;
        case "D":
          taskList.add(new Deadline(split[2], split[3], split[1]));
          break;
        case "T":
          taskList.add(new ToDos(split[2], split[1]));
          break;
        default:
          break;
      }
    }
  }

  public static ArrayList<Task> getTaskList() {
    return taskList;
  }

  /**
   * Adds the task to the taskList.
   *
   * @param task The task to be added.
   */
  public static void addToList(Task task) {
    taskList.add(task);
    UI.wrapper(
        "I have added: "
            + task.getTaskName()
            + "\n"
            + "You now have "
            + taskList.size()
            + " tasks");
  }

  /**
   * This method marks the inputted number of task as done.
   *
   * @param num The corresponding number of the task.
   */
  public static void doneAction(int num) {
    Task currTask = taskList.get(num);
    UI.wrapper(
        "I have marked "
            + currTask.getTaskName()
            + " as done.\n"
            + "You now have "
            + taskList.size()
            + " tasks left");
    currTask.mark();
  }

  /**
   * This method deletes the task from the list.
   *
   * @param num The corresponding number of the task.
   */
  public static void removeAction(int num) {
    Task currTask = taskList.get(num);
    taskList.remove(num);
    UI.wrapper(
        "I have removed"
            + currTask.getTaskName()
            + "\n"
            + "You now have "
            + taskList.size()
            + " tasks left");
  }

  /**
   * This method is used to find tasks with the matching keyword and subsequently passed to UI to
   * print it out.
   *
   * @param keyword Finding the task via the keyword provided.
   */
  public static void findTasks(String keyword) {
    ArrayList<Task> foundTasks = new ArrayList<>();
    for (Task t : taskList) {
      if (t.getTaskName().contains(keyword)) {
        foundTasks.add(t);
      }
    }
    UI.printFoundTasks(foundTasks);
  }
}
