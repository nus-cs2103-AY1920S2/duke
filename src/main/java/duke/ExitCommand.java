package duke;

public class ExitCommand extends Command {
    ExitCommand(String input){
        super(input);
    }

    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printWithFormat("", "bye", tasks);
    }

    @Override
    protected boolean isExit() {
        return true;
    }
}
