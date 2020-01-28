package tool;

public class Parser {
  private UI ui;
  private TaskList taskList;

  public Parser(UI ui, TaskList taskList) {
    this.ui = ui;
    this.taskList = taskList;
  }

  public Command parse(String input) {
    try {
      if (input.equals("bye")) {
        return new Command("bye");
      } else if (input.equals("list")) {
        return new Command("list");
      } else if (input.split(" ")[0].equals("done")) {
        return new Command("done", input.replaceFirst("done ", ""));
      } else if (input.split(" ")[0].equals("delete")) {
        return new Command("delete", input.replaceFirst("delete ", ""));
      } else if (input.split(" ")[0].equals("todo")) {
        return new Command("todo", input.replaceFirst("todo ", ""));
      } else if (input.split(" ")[0].equals("deadline")) {
        return new Command("deadline", input.replaceFirst("deadline ", ""));
      } else if (input.split(" ")[0].equals("event")) {
        return new Command("event", input.replaceFirst("event ", ""));
      } else {
        return new Command("");
      }
    } catch (Exception e) {
      return new Command("");
    }
  }
}
