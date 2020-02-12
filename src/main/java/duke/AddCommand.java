package duke;

/** AddCommand extends command and adds to the taskList based on the input. */
public class AddCommand extends Command {

  AddCommand(String input) {
    super(input);
  }

  /**
   * Executes the add command based on the task type. Adds accordingly.
   *
   * @param tasks from Tasklist initialised from duke.
   * @param ui from UI initialised from duke.
   * @param storage from storage initialised from duke.
   * @throws DukeException might throw DukeException from saveData.
   */
  protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    String[] strArr = input.split(" ");
    String type = strArr[0];
    switch (type) {
      case "deadline":
        Deadline d = new Deadline(input);
        tasks.add(d);
        ui.printWithFormat(d.toString(), "task", tasks);
        storage.saveData(tasks);
        break;
      case "event":
        Event e = new Event(input);
        tasks.add(e);
        ui.printWithFormat(e.toString(), "task", tasks);
        storage.saveData(tasks);
        break;
      case "todo":
        Todo td = new Todo(input);
        tasks.add(td);
        ui.printWithFormat(td.toString(), "task", tasks);
        storage.saveData(tasks);
        break;
      case "reminder":
        Reminder r = new Reminder(input);
        tasks.add(r);
        ui.printWithFormat(r.toString(), "task", tasks);
        storage.saveData(tasks);
        break;
    }
  }

  protected String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage)
      throws DukeException {
    String[] strArr = input.split(" ");
    String type = strArr[0];
    switch (type) {
      case "deadline":
        Deadline d = new Deadline(input);
        tasks.add(d);
        storage.saveData(tasks);
        return ui.stringWithFormat(d.toString(), "task", tasks);
      case "event":
        Event e = new Event(input);
        tasks.add(e);
        storage.saveData(tasks);
        return ui.stringWithFormat(e.toString(), "task", tasks);
      case "todo":
        Todo td = new Todo(input);
        tasks.add(td);
        assert td.toString().equals("[T][\u2718] " + getDescription(input)) : "todo is wrong";
        storage.saveData(tasks);
        return ui.stringWithFormat(td.toString(), "task", tasks);
      case "reminder":
        Reminder r = new Reminder(input);
        tasks.add(r);
        storage.saveData(tasks);
        return ui.stringWithFormat(r.toString(), "task", tasks);
      default:
        return ("Wrong format added!");
    }
  }

  protected String getDescription(String input) {
    StringBuilder str = new StringBuilder();
    String[] strArr = input.split(" ");
    for (int i = 1; i < strArr.length; i++) {
      str.append(strArr[i]).append(" ");
    }
    return str.toString();
  }
}
