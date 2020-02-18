package duke;

public class ListHelpCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return Ui.HELP_PAGE;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
