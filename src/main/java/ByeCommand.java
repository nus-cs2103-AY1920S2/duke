import java.io.IOException;

public class ByeCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        System.out.println("    Bye. Hope to see you again soon!");
        storage.writeToFile("");
    }

    public boolean isExit() {
        return true;
    }

}