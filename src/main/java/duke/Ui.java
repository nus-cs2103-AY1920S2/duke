package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
  Scanner io;

  /**
   * Initialise the Ui object with a Scanner object.
   */
  Ui() {
    this.io = new Scanner(System.in);
  }

  /**
   * Asks the user for input
   * @return String representation of said input.
   */
  public String getCommand() {
    return io.nextLine();
  }

  /**
   * Prints the Greeting.
   */
  public void greeting() {
    System.out.println("----------------------------");
    System.out.println("Hello I'm your task manager!");
    System.out.println("----------------------------\n");
  }

  /**
   * Prints the Initial Prompt.
   */
  public void initPrompt() {
    System.out.println("\n----------------------------");
    System.out.println("What tasks do you have dude?");
    System.out.println("----------------------------");
  }

  /**
   * Prints a small line
   */
  public void printSmallLine() {
    System.out.println("    -----");
  }

  /**
   * Prints bye message.
   */
  public void bye() {
    printSmallLine();
    System.out.println("    Bye bye friend!");
    printSmallLine();
  }

  /**
   * Prints the task list
   * @param tasks the task list
   */
  public void showList(ArrayList<Task> tasks, String str) {
    printSmallLine();
    System.out.println("    " + str);
    for (int i = 1; i <= tasks.size(); i++) {
      System.out.println("    " + i + ". " + tasks.get(i - 1));
    }
    printSmallLine();
  }

  /**
   * Prints the task
   * @param task task that is marked as done
   */
  public void showDone(Task task) {
    printSmallLine();
    System.out.println("    Nice! I've marked this task as done:");
    System.out.println("    " + task);
    printSmallLine();
  }

  /**
   * Prints the task
   * @param task task that is deleted
   * @param tasks the task list
   */
  public void showDelete(Task task, ArrayList<Task> tasks) {
    printSmallLine();
    System.out.println("    Nice! I've deleted this task:");
    System.out.println("    " + task);
    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
    printSmallLine();
  }

  /**
   * Prints the task
   * @param task task that is added
   * @param tasks task list
   */
  public void showAdd(Task task, ArrayList<Task> tasks) {
    printSmallLine();
    System.out.println("    Got it. I've added this task:");
    System.out.printf("    %s\n", task);
    System.out.printf("    Now you have %d tasks in the list.\n", tasks.size());
    printSmallLine();
  }

  /**
   * Prints the Exception
   * @param e Exception thrown
   */
  public void showException(Exception e) {
    printSmallLine();
    System.out.println("    " + e);
    printSmallLine();
  }

  /**
   * Prints the Exception
   * @param e An Exception that is not explicitly caught by our catches.
   */
  public void showUnknownException(Exception e) {
    printSmallLine();
    System.out.printf("    I don't know this error homie, take a look:\n    %s\n", e);
    printSmallLine();
  }
}
