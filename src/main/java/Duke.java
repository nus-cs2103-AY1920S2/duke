import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    System.out.println("Hello I'm your task manager!\n");
    System.out.println("What tasks do you have dude?\n");
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
    System.out.println("    -----");
    System.out.println("    Bye bye friend!");
    System.out.println("    -----");
  }

  public static void handleList() {
    System.out.println("    Here are the tasks in your list:");
    for (int i = 1; i <= Task.tasks.size(); i++) {
      System.out.println("    " + i + ". " + Task.tasks.get(i - 1));
    }
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

  public static void handleEvent(String desc) throws MissingTimeException {
    String[] strArr = desc.split(" /at ", 2);
    if (strArr.length == 1) throw new MissingTimeException("Event");
    String todo = strArr[0];
    String time = strArr[1];
    Event task = new Event(todo, time);
    System.out.println("    Got it. I've added this task:");
    System.out.printf("    %s\n", task);
    Task.tasks.add(task);
    System.out.printf("    Now you have %d tasks in the list.\n", Task.tasks.size());
  }

  public static void handleTodo(String desc) {
    Todo task = new Todo(desc);
    System.out.println("    Got it. I've added this task:");
    System.out.printf("    %s\n", task);
    Task.tasks.add(task);
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
    Task.tasks.add(task);
    System.out.printf("    Now you have %d tasks in the list.\n", Task.tasks.size());
  }
}
