public class NullCommand extends Command {

    public NullCommand(String command) {
        super(command);
    }

    boolean isExit() {
        return false;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("");
    }
}