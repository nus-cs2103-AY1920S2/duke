package tool;

/** Deals with making sense of the user command */
public class Parser {
  private UI ui;
  private TaskList taskList;

  public Parser(UI ui, TaskList taskList) {
    this.ui = ui;
    this.taskList = taskList;
  }

  /**
   * Parses the input string into a Command object
   *
   * @param input Input String command of the user
   * @return A Command object based on the specific command by the user
   */
  public Command parse(String input) {
    try {
      if (input.equals("bye")) {
        return new Command("bye");
      } else if (input.equals("list")) {
        return new Command("list");
      } else if (input.split(" ")[0].equals("find")) {
        return new Command("find", input.replaceFirst("find ", ""));
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
