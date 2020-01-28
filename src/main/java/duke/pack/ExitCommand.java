package duke.pack;

public class ExitCommand extends Command {

    public ExitCommand() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
        ui.exit();
    }


    public Boolean isExit() {
        return true;
    }

}
