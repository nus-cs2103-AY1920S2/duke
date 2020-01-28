package duke;

public abstract class Command {
    String input;

    Command(String input) {
        this.input = input;
    }

    protected abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    protected boolean isExit() {
        return false;
    }

}
