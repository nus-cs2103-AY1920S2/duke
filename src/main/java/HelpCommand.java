public class HelpCommand extends Command {
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.printHelp();
    }

    public boolean isExit() {
        return false;
    }
}
