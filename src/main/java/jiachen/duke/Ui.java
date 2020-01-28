package jiachen.duke;

/**
 * The Ui class handles the view and presentation layer of the app
 */
public class Ui {
  /** Print header. */
  public void printHeader() {
    String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    System.out.println("\t____________________________________________________________");
    System.out.println("\tHello! I'm Duke");
    System.out.println("\tWhat can I do for you?");
    System.out.println("\t____________________________________________________________");
  }

  /**
   * Generic print function used to print any kind of messages to the screen
   *
   * @param message the message
   */
  public void print(String message) {
    printSeparator();
    System.out.println("\t" + message);
    printSeparator();
  }

  /**
   * Print error.
   *
   * @param errorMessage the error message
   */
  public void printError(String errorMessage) {
    print(errorMessage);
  }

  /**
   * Print notification after removing task
   *
   * @param task the task
   */
  public void printRemoveTask(Task task) {
    printSeparator();
    System.out.println("\t Noted. I've removed this task: ");
    System.out.println("\t\t" + task);
    printSeparator();
  }

  /**
   * Print notification after finishing task
   *
   * @param task the task
   */
  public void printDoneTask(Task task) {
    printSeparator();
    System.out.println("\t Nice! I've marked this task as done: ");
    System.out.println("\t\t" + task);
    printSeparator();
  }

  /**
   * List all tasks in order
   *
   * @param tasks the tasks
   */
  public void printTasks(TaskList tasks) {
    printSeparator();
    for (int i = 1; i <= tasks.getList().size(); i++) {
      System.out.println("\t " + i + ". " + tasks.get(i - 1));
    }
    printSeparator();
  }

  /**
   * Print notification after adding new task
   *
   * @param task the task
   * @param numOfTasks the num of tasks
   */
  public void printNewTask(Task task, int numOfTasks) {
    printSeparator();
    System.out.println(
            "\t Got it. I've added this task: \n"
                    + "\t\t"
                    + task
                    + "\n"
                    + "\t Now you have "
                    + numOfTasks
                    + " tasks in the list.");
    printSeparator();
  }

  /** Print loading error. */
  public void printLoadingError() {
    System.out.println("\tERR: unable to load file from disk!\n");
  }

  private void printSeparator() {
    System.out.println("\t____________________________________________________________");
  }

  public void printFilteredTasks(TaskList tasks) {
    printSeparator();
    System.out.println("\tHere are the matching tasks in your list:");
    for (int i = 1; i <= tasks.getList().size(); i++) {
      System.out.println("\t " + i + ". " + tasks.get(i - 1));
    }
    printSeparator();
  }
}
