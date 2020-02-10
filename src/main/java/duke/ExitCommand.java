package duke;

/** Exits when bye is detected. isExit() is true this time. Extends the abstract class Command. */
public class ExitCommand extends Command {
  ExitCommand(String input) {
    super(input);
  }

  protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    ui.printWithFormat("", "bye", tasks);
  }

  protected String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage)
      throws DukeException {
    return ui.stringWithFormat("", "bye", tasks);
  }

  @Override
  protected boolean isExit() {
    return true;
  }
}
