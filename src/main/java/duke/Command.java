package duke;

/**
 * Command abstract class that has method signatures and shared methods for all commands.
 * Child classes implement the execute methods accordingly.
 */
public abstract class Command {
  String input;

  Command(String input) {
    this.input = input;
  }

  protected abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

  protected abstract String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage)
      throws DukeException;

  protected boolean isExit() {
    return false;
  }
}
