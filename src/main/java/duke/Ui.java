package duke;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
  Scanner io;

  Ui() {
    this.io = new Scanner(System.in);
  }

  public String getCommand() {
    return io.nextLine();
  }

  public void greeting() {
    System.out.println("----------------------------");
    System.out.println("Hello I'm your task manager!");
    System.out.println("----------------------------\n");
  }

  public void initPrompt() {
    System.out.println("\n----------------------------");
    System.out.println("What tasks do you have dude?");
    System.out.println("----------------------------");
  }

  public void printSmallLine() {
    System.out.println("    -----");
  }

  public void bye() {
    printSmallLine();
    System.out.println("    Bye bye friend!");
    printSmallLine();
  }

  public void showList(ArrayList<Task> tasks) {
    printSmallLine();
    System.out.println("    Here are the tasks in your list:");
    for (int i = 1; i <= tasks.size(); i++) {
      System.out.println("    " + i + ". " + tasks.get(i - 1));
    }
    printSmallLine();
  }

  public void showDone(Task task) {
    printSmallLine();
    System.out.println("    Nice! I've marked this task as done:");
    System.out.println("    " + task);
    printSmallLine();
  }

  public void showDelete(Task task, ArrayList<Task> tasks) {
    printSmallLine();
    System.out.println("    Nice! I've deleted this task:");
    System.out.println("    " + task);
    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
    printSmallLine();
  }

  public void showAdd(Task task, ArrayList<Task> tasks) {
    printSmallLine();
    System.out.println("    Got it. I've added this task:");
    System.out.printf("    %s\n", task);
    System.out.printf("    Now you have %d tasks in the list.\n", tasks.size());
    printSmallLine();
  }
  public void showException(Exception e) {
    printSmallLine();
    System.out.println("    " + e);
    printSmallLine();
  }
  public void showUnknownException(Exception e) {
    printSmallLine();
    System.out.printf("    I don't know this error homie, take a look:\n    %s\n", e);
    printSmallLine();
  }
}
