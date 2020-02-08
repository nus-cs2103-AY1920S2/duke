import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand(String command) {
        super(command);
    }

    boolean isExit() {
        return true;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        System.out.println("Bye. Hope to see you again soon!");
    }
}