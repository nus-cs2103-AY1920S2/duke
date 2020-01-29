import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Duke {
  public static void main(String[] args) throws IOException {
    handleLoad();
    System.out.println("----------------------------");
    System.out.println("Hello I'm your task manager!");
    System.out.println("----------------------------\n");
    handleList();
    System.out.println("\n----------------------------");
    System.out.println("What tasks do you have dude?");
    System.out.println("----------------------------");
    Scanner io = new Scanner(System.in);

    String longCommand = io.nextLine();
    String[] keywords = longCommand.split(" ", 2);

    while (!keywords[0].equals("bye")) {
      System.out.println("    -----");
      try {
        switch (keywords[0]) {
          case "list":
            handleList();
            break;

          case "done":
            handleDone(keywords[1]);
            break;

          case "delete":
            handleDelete(keywords[1]);
            break;
          case "todo":
            if (keywords.length == 1) throw new EmptyDescriptionException("Todo");
            handleTodo(keywords[1]);
            break;

          case "event":
            if (keywords.length == 1) throw new EmptyDescriptionException("Event");
            handleEvent(keywords[1]);
            break;

          case "deadline":
            if (keywords.length == 1) throw new EmptyDescriptionException("Deadline");
            handleDeadline(keywords[1]);
            break;

          default:
            throw new UnknownCommandException(keywords[0]);
        }
      } catch (EmptyDescriptionException
          | MissingTimeException
          | UnknownCommandException
          | InvalidIndexException e) {
        System.out.println("    " + e);
      } catch (Exception e) {
        System.out.printf("    I don't know this error homie, take a look:\n    %s\n", e);
      }
      System.out.println("    -----");
      longCommand = io.nextLine();
      keywords = longCommand.split(" ", 2);
    }
    saveBaby();
    System.out.println("    -----");
    System.out.println("    Bye bye friend!");
    System.out.println("    -----");
  }

  public static void saveBaby() throws IOException {
    BufferedWriter taskWriter = new BufferedWriter(new FileWriter(".//saved-tasks.txt"));
    String tasks = "";
    for (Task task: Task.tasks) {
      tasks += task.toSaveString() + "\n";
    }
    taskWriter.write(tasks);
    taskWriter.close();
  }

  public static void handleList() {
    System.out.println("    Here are the tasks in your list:");
    for (int i = 1; i <= Task.tasks.size(); i++) {
      System.out.println("    " + i + ". " + Task.tasks.get(i - 1));
    }
  }

  public static void handleLoad() throws IOException {
    BufferedReader taskLoader = new BufferedReader(new FileReader(".//saved-tasks.txt"));
    String longCommand = taskLoader.readLine();
    while (longCommand != null) {
      String[] keywords = longCommand.split(" \\|\\| ");
      Task cur = null;
      switch (keywords[1]) {
        case "todo":
          cur = new Todo(keywords[2]);
          break;
        case "deadline":
          cur = new Deadline(keywords[2], keywords[3]);
          break;
        case "event":
          cur = new Event(keywords[2], keywords[3]);
          break;
        default:
          System.out.println("error");
          break;
      }
      if (keywords[0].equals("1")) {
        cur.done();
      }
      longCommand = taskLoader.readLine();
    }
    taskLoader.close();
  }

  public static void handleDone(String keyword) throws InvalidIndexException {
    int index = 0;
    try {
      index = Integer.parseInt(keyword) - 1;
      Task.tasks.get(index).done();
    } catch (Exception e) {
      throw new InvalidIndexException(keyword);
    }
    System.out.println("    Nice! I've marked this task as done:");
    System.out.println("    " + Task.tasks.get(index));
  }

  public static void handleDelete(String keyword) throws InvalidIndexException {
    int index = 0;
    Task delete = null;
    try {
      index = Integer.parseInt(keyword) - 1;
      delete = Task.tasks.get(index);
      Task.tasks.remove(delete);
    } catch (Exception e) {
      throw new InvalidIndexException(keyword);
    }
    System.out.println("    Nice! I've deleted this task:");
    System.out.println("    " + delete);
    System.out.println("    Now you have " + Task.tasks.size() + " tasks in the list.");
  }

  public static void handleEvent(String desc) throws MissingTimeException {
    String[] strArr = desc.split(" /at ", 2);
    if (strArr.length == 1) throw new MissingTimeException("Event");
    String todo = strArr[0];
    String time = strArr[1];
    Event task = new Event(todo, time);
    System.out.println("    Got it. I've added this task:");
    System.out.printf("    %s\n", task);
    System.out.printf("    Now you have %d tasks in the list.\n", Task.tasks.size());
  }

  public static void handleTodo(String desc) {
    Todo task = new Todo(desc);
    System.out.println("    Got it. I've added this task:");
    System.out.printf("    %s\n", task);
    System.out.printf("    Now you have %d tasks in the list.\n", Task.tasks.size());
  }

  public static void handleDeadline(String desc) throws MissingTimeException {
    String[] strArr = desc.split(" /by ", 2);
    if (strArr.length == 1) throw new MissingTimeException("Deadline");
    String todo = strArr[0];
    String time = strArr[1];
    Deadline task = new Deadline(todo, time);
    System.out.println("    Got it. I've added this task:");
    System.out.printf("    %s\n", task);
    System.out.printf("    Now you have %d tasks in the list.\n", Task.tasks.size());
  }
}
